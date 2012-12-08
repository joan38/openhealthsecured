package agent.thermometer;

import es.libresoft.openhealth.chap.AgentAuthenticator;
import ieee_11073.part_20601.asn1.*;
import java.util.Calendar;

public class Thermometer {

    private static AbsoluteTime generateAbsoluteTime() {
        AbsoluteTime at = new AbsoluteTime();
        Calendar c = Calendar.getInstance();
        at.setSec_fractions(new INT_U8(c.get(Calendar.MILLISECOND)));
        at.setSecond(new INT_U8(c.get(Calendar.SECOND)));
        at.setMinute(new INT_U8(c.get(Calendar.MINUTE)));
        at.setHour(new INT_U8(c.get(Calendar.HOUR_OF_DAY)));
        at.setDay(new INT_U8(c.get(Calendar.DAY_OF_MONTH)));
        at.setMonth(new INT_U8(c.get(Calendar.MONTH) + 1));
        at.setYear(new INT_U8(c.get(Calendar.YEAR) % 100));
        return at;
    }

    private static int generateSimpleNuObservedValueInteger() {
        return 36 + (int) (Math.random() * 4);
    }

    public static ObservationScanFixed createObservationScanFixed(ObservationScanFixed osf1) throws Exception {
        byte[] osfval;
        osfval = new byte[11];
        AbsoluteTime at = generateAbsoluteTime();

        osfval[0] = (byte) 0x00;
        osfval[1] = (byte) generateSimpleNuObservedValueInteger();
        osfval[2] = (byte) 0x00;
        osfval[3] = (byte) at.getYear().getValue().byteValue();
        osfval[4] = (byte) at.getMonth().getValue().byteValue();
        osfval[5] = (byte) at.getDay().getValue().byteValue();
        osfval[6] = (byte) at.getHour().getValue().byteValue();
        osfval[7] = (byte) at.getMinute().getValue().byteValue();
        osfval[8] = (byte) at.getSecond().getValue().byteValue();
        osfval[9] = (byte) at.getSec_fractions().getValue().byteValue();
        osfval[10] = (byte) 0x00;

        HANDLE handle = new HANDLE();
        handle.setValue(new INT_U16(1));
        osf1.setObj_handle(handle);
        osf1.setObs_val_data(osfval);
        return osf1;
    }

    private static boolean validateNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean validatePort(String arg) {

        if (validateNumber(arg)) {
            if (Integer.parseInt(arg) > 0 || Integer.parseInt(arg) < 65536) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    private static boolean validateType(String arg) {
        if (validateNumber(arg)) {
            if (Integer.parseInt(arg) == 800 || Integer.parseInt(arg) == 16386) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static int[] idType(int type) {
        int[] value;
        value = new int[3];

        if (type == 800) {
            value[0] = 83;
            value[1] = 84;
            value[2] = 68;
        } else {
            value[0] = 69;
            value[1] = 88;
            value[2] = 84;
        }
        
        return value;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        try {
            if (args.length < 4) {
                System.out.println("Thermometer Agent need arguments: <Thermometer Type> <Manager IP> <Manager Port>");

            } else {
                if (validateType(args[0])) {
                    if (!args[1].isEmpty()) {
                        if (validatePort(args[2])) {
                            int type = Integer.parseInt(args[0]);
                            String host = args[1];
                            int port = Integer.parseInt(args[2]);
                            byte[] key = args[3].getBytes();
                            AgentAuthenticator authenticator = new AgentAuthenticator(key);
                            ReceiverThread receiver = new ReceiverThread(authenticator);
                            receiver.createAssociationRequest(type, host, port, idType(type));
                        } else {
                            System.out.println("You must be to write a valid TCP port");
                        }
                    } else {
                        System.out.println("You must be to write the Manager IP");
                    }
                } else {
                    System.out.println("You must be to write a valid thermometer type: 800 or 16386");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid input data");
            e.printStackTrace();
        }
    }

    public static void getErrorResult(int errorresult) {
        try {
            System.out.println("Error Result");

            if (errorresult == 1) {
                System.out.println("No Such Object Instance");

            } else if (errorresult == 9) {
                System.out.println("No Such Action");

            } else if (errorresult == 17) {
                System.out.println("Invalid Object Instance");

            } else if (errorresult == 23) {
                System.out.println("Protocol Violation");

            } else if (errorresult == 24) {
                System.out.println("Not Allowed by Object");

            } else if (errorresult == 25) {
                System.out.println("Action TimeOut");

            } else if (errorresult == 26) {
                System.out.println("Action Aborted");

            } else {
                System.out.println("Unknown");
            }
        } catch (Exception e) {
            System.out.println("Error: getErrorResult");
            e.printStackTrace();
        }
    }
}