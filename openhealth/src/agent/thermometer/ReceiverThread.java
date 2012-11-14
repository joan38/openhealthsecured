package agent.thermometer;

import ieee_11073.part_10101.Nomenclature;
import ieee_11073.part_20601.asn1.AVA_Type;
import ieee_11073.part_20601.asn1.AareApdu;
import ieee_11073.part_20601.asn1.AarqApdu;
import ieee_11073.part_20601.asn1.AbrtApdu;
import ieee_11073.part_20601.asn1.ApduType;
import ieee_11073.part_20601.asn1.AssociationVersion;
import ieee_11073.part_20601.asn1.AttributeList;
import ieee_11073.part_20601.asn1.ConfigId;
import ieee_11073.part_20601.asn1.ConfigObject;
import ieee_11073.part_20601.asn1.ConfigObjectList;
import ieee_11073.part_20601.asn1.ConfigReport;
import ieee_11073.part_20601.asn1.ConfigReportRsp;
import ieee_11073.part_20601.asn1.DataApdu;
import ieee_11073.part_20601.asn1.DataProto;
import ieee_11073.part_20601.asn1.DataProtoId;
import ieee_11073.part_20601.asn1.DataProtoList;
import ieee_11073.part_20601.asn1.DataReqId;
import ieee_11073.part_20601.asn1.DataReqModeCapab;
import ieee_11073.part_20601.asn1.DataReqModeFlags;
import ieee_11073.part_20601.asn1.EncodingRules;
import ieee_11073.part_20601.asn1.EventReportArgumentSimple;
import ieee_11073.part_20601.asn1.FunctionalUnits;
import ieee_11073.part_20601.asn1.HANDLE;
import ieee_11073.part_20601.asn1.INT_U16;
import ieee_11073.part_20601.asn1.INT_U32;
import ieee_11073.part_20601.asn1.InvokeIDType;
import ieee_11073.part_20601.asn1.NomenclatureVersion;
import ieee_11073.part_20601.asn1.OID_Type;
import ieee_11073.part_20601.asn1.ObservationScanFixed;
import ieee_11073.part_20601.asn1.PhdAssociationInformation;
import ieee_11073.part_20601.asn1.ProtocolVersion;
import ieee_11073.part_20601.asn1.PrstApdu;
import ieee_11073.part_20601.asn1.RelativeTime;
import ieee_11073.part_20601.asn1.ReleaseResponseReason;
import ieee_11073.part_20601.asn1.RlreApdu;
import ieee_11073.part_20601.asn1.RlrqApdu;
import ieee_11073.part_20601.asn1.ScanReportInfoFixed;
import ieee_11073.part_20601.asn1.SystemType;
import ieee_11073.part_20601.asn1.DataApdu.MessageChoiceType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import org.bn.CoderFactory;
import org.bn.IDecoder;
import org.bn.IEncoder;
import org.bn.types.BitString;

public class ReceiverThread {

    private static Socket socket;
    public final static String ENCODING_RULE = "MDER";

    public ReceiverThread() {
    }

    private boolean checkSocketState() {
        if (socket.isConnected() && !socket.isClosed()) {
            return true;
        }
        try {
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Disconnected");
        System.exit(0);
        return false;
    }

    public void evalueResponse(ApduType apdu) {
        try {
            checkSocketState();
            if (apdu.isAareSelected()) {
                System.out.println("isAare");
                evalueAssociationResponse(apdu);

            } else if (apdu.isPrstSelected()) {
                System.out.println("isPrst");
                evaluePresentationResponse(apdu);

            } else if (apdu.isRlreSelected()) {
                evalueAssociationReleaseResponse(apdu);

            } else if (apdu.isAbrtSelected()) {
                evalueAbortReason(apdu);

            } else if (apdu.isRlrqSelected()) {
                evalueAssociationReleaseRequest(apdu);

            } else if (apdu.isAarqSelected()) {
                evalueAssociationRequest();

            } else {
                System.out.println("Something not good!");
            }

        } catch (Exception e) {
            System.out.println("Error: evalueResponse");
            e.printStackTrace();
        }

    }

    public void evalueAssociationResponse(ApduType apdu) {

        try {

            System.out.println("Initializing ...");
            AareApdu aare = apdu.getAare();
            System.out.println("AssociateResult: " + aare.getResult().getValue());
            int associateresult = aare.getResult().getValue();
            DataProto dataproto = aare.getSelected_data_proto();
            System.out.println("DataProtoID: " + dataproto.getData_proto_id().getValue());


            if (associateresult == 0) {
                associateResultAccepted();

            } else if (associateresult == 1) {
                associateResultRejectedPermanent();

            } else if (associateresult == 2) {
                associateResultRejectedTransient();

            } else if (associateresult == 3) {
                associationResultAcceptedUnknownConfig(dataproto);

            } else if (associateresult == 4) {
                associateResultRejectedNoCommonProtocol(dataproto);

            } else if (associateresult == 5) {
                associateResultRejectedNoCommonParameter(dataproto);

            } else if (associateresult == 6) {
                associateResultRejectedUnknown();

            } else if (associateresult == 7) {
                associateResultRejectedUnauthorized();

            } else if (associateresult == 8) {
                associateResultUnsupportedAssociationVersion(dataproto);

            } else {
                System.out.println("Unknown Message");
            }
        } catch (Exception e) {
            System.out.println("Error: evalueAssociationResponse");
            e.printStackTrace();
        }
    }

    public void evalueAbortReason(ApduType apdu) {

        try {

            System.out.println("Initializing ...");
            AbrtApdu abrt = apdu.getAbrt();
            System.out.println("AbortReason: " + abrt.getReason().getValue());
            int reason = abrt.getReason().getValue();

            if (reason == 0) {
                System.out.println("Undefined");
            } else if (reason == 1) {
                System.out.println("Buffer overflow");
            } else if (reason == 2) {
                System.out.println("Response timeout");
            } else if (reason == 3) {
                System.out.println("Configuration timeout");
            } else {
                System.out.println("Unknown Message");
            }
            socket.close();
        } catch (Exception e) {
            System.out.println("Error: evalueAbortReason");
            e.printStackTrace();
        }
    }

    public void evalueAssociationReleaseResponse(ApduType apdu) {

        try {

            System.out.println("Is Rlre ...");
            RlreApdu rlre = apdu.getRlre();
            int reason = rlre.getReason().getValue();
            System.out.println("AssociationReleaseResponseReason: " + reason);

            if (reason == 0) {
                System.out.println("Normal");
                socket.close();
            } else {
                System.out.println("Problem!");
            }
        } catch (Exception e) {
            System.out.println("Error: evalueAssociationReleaseResponse");
            e.printStackTrace();
        }
    }

    public void evalueAssociationReleaseRequest(ApduType apdu) {

        try {

            System.out.println("Is Rlrq ...");
            RlrqApdu rlrq = apdu.getRlrq();
            int reason = rlrq.getReason().getValue();
            System.out.println("AssociationReleaseRequestReason: " + reason);

            if (reason == 0) {
                System.out.println("Normal");
                /* Make ApduType */
                ApduType at = new ApduType();

                /* Make ApduType -> RlreApdu */
                RlreApdu rlre = new RlreApdu();

                /* Make ApduType -> RlrqApdu -> ReleaseResponseReason Normal(0) NoMoreConfs(1) ConfChanges(2) */
                ReleaseResponseReason rrr = new ReleaseResponseReason(0);

                /* Add ReleaseRequestReason to ApduType -> AarqApdu -> ReleaseResponseReason */
                rlre.setReason(rrr);

                /* Add RlrqApdu to ApduType -> RlreApdu */
                at.selectRlre(rlre);
                SenderThread.sendApdu(at);

            } else {
                System.out.println("Problem!");
            }
        } catch (Exception e) {
            System.out.println("Error: evalueAssociationReleaseRequest");
            e.printStackTrace();
        }

    }

    public void evalueAssociationRequest() {
        System.out.println("Is Aarq ...");

    }

    public void evaluePresentationResponse(ApduType apdu) {

        try {
            IDecoder decoder = CoderFactory.getInstance().newDecoder(ENCODING_RULE);

            System.out.println("Response ...");
            PrstApdu prstr = apdu.getPrst();
            byte[] datar = prstr.getValue();
            ByteArrayInputStream input2 = new ByteArrayInputStream(datar);
            DataApdu dar = decoder.decode(input2, DataApdu.class);
            System.out.println("Invoke-Id: " + dar.getInvoke_id().getValue());
            if (dar.getMessage().isRors_cmip_confirmed_event_reportSelected()) {
                System.out.println("Event Report Result Simple");
                System.out.println("Object Handle: " + dar.getMessage().getRors_cmip_confirmed_event_report().getObj_handle().getValue().getValue());
                System.out.println("OID-Type: " + dar.getMessage().getRors_cmip_confirmed_event_report().getEvent_type().getValue().getValue());
                System.out.println("Time: " + dar.getMessage().getRors_cmip_confirmed_event_report().getCurrentTime().getValue().getValue());

                if (dar.getMessage().getRors_cmip_confirmed_event_report().getEvent_type().getValue().getValue() == Nomenclature.MDC_NOTI_CONFIG) {
                    getConfigReportRsp(dar.getMessage().getRors_cmip_confirmed_event_report().getEvent_reply_info());

                } else {
                    acceptedConfig();
                }

            } else if (dar.getMessage().isRors_cmip_confirmed_actionSelected()) {
                System.out.println("Action Result Simple");

            } else if (dar.getMessage().isRors_cmip_confirmed_setSelected()) {
                System.out.println("Set Result Simple");

            } else if (dar.getMessage().isRors_cmip_getSelected()) {
                System.out.println("Get Result Simple");

            } else if (dar.getMessage().isRoerSelected()) {

                Thermometer.getErrorResult(dar.getMessage().getRoer().getError_value().getValue());

            } else if (dar.getMessage().isRorjSelected()) {
                System.out.println("Reject Result");

            } else {
                System.out.println("Problem!");
            }
        } catch (Exception e) {
            System.out.println("Error: evaluePresentationResponse");
            e.printStackTrace();
        }
    }

    public void getConfigReportRsp(byte[] configrsp) {

        try {

            IDecoder decoderCR = CoderFactory.getInstance().newDecoder(ENCODING_RULE);

            ByteArrayInputStream input3 = new ByteArrayInputStream(configrsp);
            ConfigReportRsp cfgrr = decoderCR.decode(input3, ConfigReportRsp.class);
            System.out.println("Config-Id: " + cfgrr.getConfig_report_id().getValue());
            if (cfgrr.getConfig_result().getValue() == 0) {
                acceptedConfig();
            } else if (cfgrr.getConfig_result().getValue() == 1) {
                System.out.println("Unsupported Config");
            } else if (cfgrr.getConfig_result().getValue() == 2) {
                System.out.println("Standard Config Unknown");
            } else {
                System.out.println("Unknown!");
            }

        } catch (Exception e) {
            System.out.println("Error: getConfigReportRsp");
            e.printStackTrace();
        }

    }

    public void acceptedConfig() {

        try {

            System.out.println("Accepted Config");
            /* Make AdpuType */
            ApduType at3 = new ApduType();

            /* Choice Presentation APDU */
            PrstApdu prst2 = new PrstApdu();

            /* Make DataApdu */
            DataApdu da2 = new DataApdu();

            /* DataApdu -> InvokeIDType */
            InvokeIDType iit2 = new InvokeIDType(8);

            /* DataApdu -> MessageChoiceType */
            MessageChoiceType mct2 = new MessageChoiceType();

            /* DataApdu -> MessageChoiceType -> EventReportArgumentSimple */
            EventReportArgumentSimple eras2 = new EventReportArgumentSimple();

            /* DataApdu -> MessageChoiceType -> EventReportArgumentSimple -> HANDLE */
            HANDLE handle3 = new HANDLE();

            /* Set DataApdu -> MessageChoiceType -> EventReportArgumentSimple -> HANDLE  [Standard Configuration, 0] */
            handle3.setValue(new INT_U16(0));
            eras2.setObj_handle(handle3);

            /* Make DataApdu -> MessageChoiceType -> EventReportArgumentSimple -> RelativeTime */
            RelativeTime rt2 = new RelativeTime();

            /* Set DataApdu -> MessageChoiceType -> EventReportArgumentSimple -> RelativeTime [Unsupport, OxFFFFFFFF] */
            rt2.setValue(new INT_U32(0L));
            eras2.setEvent_time(rt2);

            /* Make DataApdu -> MessageChoiceType -> EventReportArgumentSimple -> OID_Type */
            OID_Type oidt71 = new OID_Type();

            /* Set DataApdu -> MessageChoiceType -> EventReportArgumentSimple -> OID_Type [,] */
            /* Set with MDC_NOTI_CONFIG [3356]*/
            oidt71.setValue(new INT_U16(Nomenclature.MDC_NOTI_SCAN_REPORT_FIXED));
            eras2.setEvent_type(oidt71);


            /* Make DataApdu -> EventReportArgumentSimple -> ScanReportInfoFixed */
            ScanReportInfoFixed srif = new ScanReportInfoFixed();

            /* Make DataApdu -> EventReportArgumentSimple -> ScanReportInfoFixed -> DataReqId */
            DataReqId dri = new DataReqId();

            /* Set DataApdu -> EventReportArgumentSimple -> ScanReportInfoFixed -> DataReqId */
            dri.setValue(61440);
            srif.setData_req_id(dri);

            /* Set DataApdu -> EventReportArgumentSimple -> ScanReportInfoFixed -> ScanReportNo */
            srif.setScan_report_no(0);

            /* Set DataApdu -> EventReportArgumentSimple -> ScanReportInfoFixed -> ObservationScanFixed */

            java.util.Collection<ObservationScanFixed> osfc = new java.util.LinkedList<ObservationScanFixed>();
            ObservationScanFixed osf1 = new ObservationScanFixed();

            osfc.add(Thermometer.createObservationScanFixed(osf1));
            srif.setObs_scan_fixed(osfc);

            /* Encode ScanReportInfoFixed */
            ByteArrayOutputStream outsrif = new ByteArrayOutputStream();
            IEncoder<ScanReportInfoFixed> encoderSRIF = CoderFactory.getInstance().newEncoder(ENCODING_RULE);
            encoderSRIF.encode(srif, outsrif);
            /*End encode ScanReportInfoFixed */

            /* Add ScanReportInfoFixed in EventReportArgumentSimple->EventInfo */
            eras2.setEvent_info(outsrif.toByteArray());

            /* Add EventReportArgumentSimple to  DataApdu -> MessageChoiceType */
            mct2.selectRoiv_cmip_confirmed_event_report(eras2);

            /* Add MessageChoiceType to DataApdu */
            da2.setInvoke_id(iit2);
            da2.setMessage(mct2);

            /* Encode DataApdu */
            ByteArrayOutputStream outda2 = new ByteArrayOutputStream();
            IEncoder<DataApdu> encoderDA2 = CoderFactory.getInstance().newEncoder(ENCODING_RULE);
            encoderDA2.encode(da2, outda2);
            /*End encode ScanReportInfoFixed */

            /* Add DataApdu in PsrtApdu */
            prst2.setValue(outda2.toByteArray());

            /* Add PrstApdu to ApduType -> PrstApdu */
            at3.selectPrst(prst2);

            SenderThread.sendApdu(at3);

        } catch (Exception e) {
            System.out.println("Error: acceptedConfig");
            e.printStackTrace();
        }
    }

    public void associationResultAcceptedUnknownConfig(DataProto dataproto) {

        try {

            System.out.println("Accepted Unknown Config");

            /* Configuration Information Exchange */
            /* Thermometer Extended Configuration */

            /* Make AdpuType */
            ApduType at2 = new ApduType();

            /* Choice Presentation APDU */
            PrstApdu prst = new PrstApdu();

            /* Make DataApdu */
            DataApdu da = new DataApdu();

            /* DataApdu -> InvokeIDType */
            InvokeIDType iit = new InvokeIDType(1);

            /* DataApdu -> MessageChoiceType */
            MessageChoiceType mct = new MessageChoiceType();

            /* DataApdu -> MessageChoiceType -> EventReportArgumentSimple */
            EventReportArgumentSimple eras = new EventReportArgumentSimple();

            /* DataApdu -> MessageChoiceType -> EventReportArgumentSimple -> HANDLE */
            HANDLE handle1 = new HANDLE();

            /* Set DataApdu -> MessageChoiceType -> EventReportArgumentSimple -> HANDLE  [Standard Configuration, 0] */
            handle1.setValue(new INT_U16(0));
            eras.setObj_handle(handle1);

            /* Make DataApdu -> MessageChoiceType -> EventReportArgumentSimple -> RelativeTime */
            RelativeTime rt = new RelativeTime();

            /* Set DataApdu -> MessageChoiceType -> EventReportArgumentSimple -> RelativeTime [Unsupport, OxFFFFFFFF] */
            rt.setValue(new INT_U32(0L));

            /* Make DataApdu -> MessageChoiceType -> EventReportArgumentSimple -> OID_Type */
            OID_Type oidt1 = new OID_Type();

            /* Set DataApdu -> MessageChoiceType -> EventReportArgumentSimple -> OID_Type [,] */
            /* Set with MDC_NOTI_CONFIG [3356]*/
            oidt1.setValue(new INT_U16(Nomenclature.MDC_NOTI_CONFIG));
            eras.setEvent_type(oidt1);

            /* Make DataApdu -> EventReportArgumentSimple -> ConfigReport */
            ConfigReport cfgrp = new ConfigReport();

            /* Make DataApdu -> EventReportArgumentSimple -> ConfigReport -> ConfigId  STD [800] EXT[16386-]*/
            ConfigId cfgid = new ConfigId(16386);

            /* Make DataApdu -> EventReportArgumentSimple -> ConfigReport -> ConfigObjectList */
            ConfigObjectList col = new ConfigObjectList();

            /* Initialize DataApdu -> EventReportArgumentSimple -> ConfigReport -> ConfigObjectList */
            col.initValue();

            /* Make DataApdu -> EventReportArgumentSimple -> ConfigReport -> ConfigObjectList -> ConfigObject */
            ConfigObject co = new ConfigObject();

            /* DataApdu -> MessageChoiceType -> EventReportArgumentSimple -> ConfigReport -> ConfigObjectList -> ConfigObject -> HANDLE */
            HANDLE handle2 = new HANDLE();

            /* Set DataApdu -> EventReportArgumentSimple -> ConfigReport -> ConfigObjectList -> ConfigObject -> HANDLE */

            handle2.setValue(new INT_U16(1));
            co.setObj_handle(handle2);

            /* Set DataApdu -> EventReportArgumentSimple -> ConfigReport -> ConfigObjectList -> ConfigObject -> OID_Type */
            /* Set with MDC_MOC_VMO_METRIC [4]*/
            /* Set with MDC_MOC_VMO_METRIC_NU [6]*/
            //oidt.setValue(new INT_U16(6));
		/* Make DataApdu -> MessageChoiceType -> EventReportArgumentSimple -> ConfigReport -> ConfigObjectList -> ConfigObject -> OID_Type */
            OID_Type oidt2 = new OID_Type();
            oidt2.setValue(new INT_U16(Nomenclature.MDC_MOC_VMO_METRIC_NU));
            co.setObj_class(oidt2);

            /* Make DataApdu -> EventReportArgumentSimple -> ConfigReport -> ConfigObjectList -> ConfigObject -> AttributeList */
            AttributeList alc = new AttributeList();

            /* Set AttributeList */
            /* Add AttributeList in ConfigObject */
            co.setAttributes(setAttributeList(alc));

            /* Add ConfigObject in ConfigObjectList */
            col.add(co);

            /* Add ConfigObjectList in ConfigReport */
            cfgrp.setConfig_obj_list(col);

            /* Add ConfigId in ConfigReport */
            cfgrp.setConfig_report_id(cfgid);

            /* Encode ConfigReport */
            ByteArrayOutputStream outcr = new ByteArrayOutputStream();
            IEncoder<ConfigReport> encoderCR = CoderFactory.getInstance().newEncoder(ENCODING_RULE);
            encoderCR.encode(cfgrp, outcr);
            /*End encode ConfigReport */

            /* Add ConfigReport in EventReportArgumentSimple->ConfigReport */
            eras.setEvent_info(outcr.toByteArray());

            /* Add RelativeTime EventReportArgumentSimpleA->RelativeTime */
            eras.setEvent_time(rt);

            /* Add EventReportArgumentSimple in MessageChoiceType */
            /* Set Confirmed Event Report */
            mct.selectRoiv_cmip_confirmed_event_report(eras);

            /* Add MessageChoiceType in DataApdu */
            da.setMessage(mct);

            /* Add InvokeIdType in DataApdu */
            da.setInvoke_id(iit);

            /* Encode DataApdu */
            ByteArrayOutputStream outda = new ByteArrayOutputStream();
            IEncoder<DataApdu> encoderPER = CoderFactory.getInstance().newEncoder(ENCODING_RULE);
            encoderPER.encode(da, outda);
            /*End encode DataApdu */


            /* Add DataApdu PrstApdu */
            prst.setValue(outda.toByteArray());

            /* Add PrstApdu to ApduType -> PrstApdu */
            at2.selectPrst(prst);

            SenderThread.sendApdu(at2);

        } catch (Exception e) {
            System.out.println("Error: associationResultAcceptedUnknownConfig");
            e.printStackTrace();
        }

    }

    public AttributeList setAttributeList(AttributeList alc) throws Exception {

        /* Initialize DataApdu -> EventReportArgumentSimple -> ConfigReport -> ConfigObjectList -> ConfigObject -> AttributeList */
        alc.initValue();

        java.util.Collection<AVA_Type> cavaelc = new java.util.LinkedList<AVA_Type>();
        AVA_Type cavael1 = new AVA_Type();
        AVA_Type cavael2 = new AVA_Type();
        AVA_Type cavael3 = new AVA_Type();
        AVA_Type cavael4 = new AVA_Type();
        AVA_Type cavael5 = new AVA_Type();
        AVA_Type cavael6 = new AVA_Type();

        cavael1.initWithDefaults();
        cavael2.initWithDefaults();
        cavael3.initWithDefaults();
        cavael4.initWithDefaults();
        cavael5.initWithDefaults();
        cavael6.initWithDefaults();

        /* OID_Type TEMP for ID Attributes */
        OID_Type oidtemp1 = new OID_Type();
        OID_Type oidtemp2 = new OID_Type();
        OID_Type oidtemp3 = new OID_Type();
        OID_Type oidtemp4 = new OID_Type();


        /* Byte Array NULL for Value Attributes */
        byte[] btmp1 = {(byte) 0, (byte) 2, (byte) 75, (byte) 92};
        byte[] btmp2 = {(byte) 240, (byte) 64};
        byte[] btmp3 = {(byte) 23, (byte) 160};
        byte[] btmp4 = {(byte) 0, (byte) 2, (byte) 0, (byte) 8, (byte) 10, (byte) 76, (byte) 0, (byte) 2, (byte) 9, (byte) 144, (byte) 0, (byte) 8};


        /* Add MDC_ATTR_ID_TYPE [2351] */
        oidtemp1.setValue(new INT_U16(Nomenclature.MDC_ATTR_ID_TYPE));
        cavael1.setAttribute_id(oidtemp1);
        cavael1.setAttribute_value(btmp1);
        cavaelc.add(cavael1);


        /* Add MDC_ATTR_METRIC_SPEC_SMALL [2630] */
        oidtemp2.setValue(new INT_U16(Nomenclature.MDC_ATTR_METRIC_SPEC_SMALL));
        cavael2.setAttribute_id(oidtemp2);
        cavael2.setAttribute_value(btmp2);
        cavaelc.add(cavael2);


        /* Add MDC_ATTR_UNIT_CODE [2454] */
        oidtemp3.setValue(new INT_U16(Nomenclature.MDC_ATTR_UNIT_CODE));
        cavael3.setAttribute_id(oidtemp3);
        cavael3.setAttribute_value(btmp3);
        cavaelc.add(cavael3);


        /* Add MDC_ATTR_ATTRIBUTE_VAL_MAP [2645] */
        oidtemp4.setValue(new INT_U16(Nomenclature.MDC_ATTR_ATTRIBUTE_VAL_MAP));
        cavael4.setAttribute_id(oidtemp4);
        cavael4.setAttribute_value(btmp4);
        cavaelc.add(cavael4);

        alc.setValue(cavaelc);
        return alc;
    }

    public void associateResultUnsupportedAssociationVersion(DataProto dataproto) {

        try {

            System.out.println("Unsupported Association Version");

            if (dataproto.getData_proto_id().getValue() == 0) {
                byte[] phdinfo = dataproto.getData_proto_info();
                System.out.println("DataProtoInfo: " + (getHexString(phdinfo)));
                if (getHexString(phdinfo).isEmpty()) {
                    System.out.println("OK!");
                } else {
                    System.out.println("DataProtoInfo Must Be Null");
                }
            } else {
                System.out.println("DataProtoId Must Have Value : 0");
            }
        } catch (Exception e) {
            System.out.println("Error: associateResultUnsupportedAssociationVersion");
            e.printStackTrace();
        }

    }

    public void associateResultRejectedUnauthorized() {
        System.out.println("Rejected Unauthorized");
    }

    public void associateResultRejectedUnknown() {

        System.out.println("Rejected Unknown");

    }

    public void associateResultRejectedNoCommonParameter(DataProto dataproto) {

        try {

            System.out.println("Rejected No Common Parameter");

            if (dataproto.getData_proto_id().getValue() == 0) {
                byte[] phdinfo = dataproto.getData_proto_info();
                System.out.println("DataProtoInfo: " + (getHexString(phdinfo)));
                if (getHexString(phdinfo).isEmpty()) {
                    System.out.println("OK!");
                } else {
                    System.out.println("DataProtoInfo Must Be Null");
                }
            } else {
                System.out.println("DataProtoId Must Have Value : 0");
            }
        } catch (Exception e) {
            System.out.println("Error: associateResultRejectedNoCommonParameter");
            e.printStackTrace();
        }
    }

    public void associateResultRejectedNoCommonProtocol(DataProto dataproto) {

        try {

            System.out.println("Rejected No Common Protocol");

            if (dataproto.getData_proto_id().getValue() == 0) {
                byte[] phdinfo = dataproto.getData_proto_info();
                System.out.println("DataProtoInfo: " + (getHexString(phdinfo)));
                if (getHexString(phdinfo).isEmpty()) {
                    System.out.println("OK!");
                } else {
                    System.out.println("DataProtoInfo Must Be Null");
                }
            } else {
                System.out.println("DataProtoId Must Have Value : 0");
            }
        } catch (Exception e) {
            System.out.println("Error: associateResultRejectedNoCommonProtocol");
            e.printStackTrace();
        }
    }

    public void associateResultRejectedTransient() {
        System.out.println("Rejected Transient");
    }

    public void associateResultRejectedPermanent() {
        System.out.println("Rejected Permanent");
    }

    public void associateResultAccepted() {

        try {

            acceptedConfig();

        } catch (Exception e) {
            System.out.println("Error: associateResultAccepted");
            e.printStackTrace();
        }
    }

    public void createAssociationRequest(int type, String host, int port, int[] esid) {

        try {

            /* TEST ApduType choice */
            /* Make ApduType */
            ApduType at = new ApduType();

            /* Make ApduType -> AarqApdu */
            AarqApdu aarq = new AarqApdu();

            /* Make ApduType -> AarqApdu -> AssociationVersion */
            AssociationVersion av = new AssociationVersion();

            /* Make ApduType -> AarqApdu -> AssociationVersion -> Bits-32 1(0)*/
            byte[] ascVr = {(byte) 128, (byte) 0, (byte) 0, (byte) 0};

            /* Set Value AssociationVersion */
            BitString bt = new BitString(ascVr);
            av.setValue(bt);

            /* Make ApduType -> AarqApdu -> DataProtoList */
            DataProtoList dpl = new DataProtoList();

            /* Initialize  DataProtoList */
            dpl.initValue();

            /* Make ApduType -> AarqApdu -> DataProtoList -> DataProto */
            DataProto dp = new DataProto();

            /* Make ApduType -> AarqApdu -> DataProtoList -> DataProto -> DataProtoId */
            DataProtoId dpi = new DataProtoId(20601);

            /*BEGIN to encode PhdAssociationInformation in ANY form through ByteArrayOutputStream to get bytes array*/

            /* Make PhdAssociationInformation */
            PhdAssociationInformation pai = new PhdAssociationInformation();

            /* Make PhdAssociationInformation -> ProtocolVersion */
            ProtocolVersion pv = new ProtocolVersion();

            /* Make PhdAssociationInformation -> ProtocolVersion --> Bits-32 1(0) */
            byte[] proVr = {(byte) 128, (byte) 0, (byte) 0, (byte) 0};

            /* Set Value PhdAssociationInformation -> ProtocolVersion */
            BitString pbt = new BitString(proVr);
            pv.setValue(pbt);

            /* Make 16 BITS  MDER 0(0) [128], XER 1(0) [192], PER 2(0) [224](32)*/
            byte[] encR = {(byte) 128, (byte) 0};

            /* Make & Set, PhdAssociationInformation -> EncodingRules -> Bits-16 1(0) */
            EncodingRules er = new EncodingRules(new BitString(encR));

            /* Make & Set PhdAssociationInformation -> NomenclatureVersion -> Bits-32 1(0) */
            byte[] noVr = {(byte) 128, (byte) 0, (byte) 0, (byte) 0};

            /* Make & Set PhdAssociationInformation -> NomenclatureVersion*/
            BitString nbt = new BitString(noVr);
            NomenclatureVersion nv = new NomenclatureVersion(new BitString(noVr));

            /* Make PhdAssociationInformation -> FunctionalUnits -> Bits-32 0(0) */
            byte[] byteArray2 = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
            FunctionalUnits fn = new FunctionalUnits(new BitString(byteArray2));

            /* Make PhdAssociationInformation -> SystemType -> Bits-32 8(0)*/
            byte[] syst = {(byte) 0, (byte) 128, (byte) 0, (byte) 0};
            SystemType st = new SystemType(new BitString(syst));

            byte[] sysId = {(byte) 76, (byte) 73, (byte) 66, (byte) 82, (byte) 69, (byte) 95, (byte) 65, (byte) 71, (byte) 69, (byte) 78, (byte) 84, (byte) 95, (byte) esid[0], (byte) esid[1], (byte) esid[2]};

            /* Make PhdAssociationInformation -> ConfigId  Thermometer Range Value Std[800] Ext[16386,32767]*/
            ConfigId cid = new ConfigId(type);


            /* Make PhdAssociationInformation -> DataReqModeCapab */
            DataReqModeCapab drmc = new DataReqModeCapab();

            /* Make PhdAssociationInformation -> DataReqModeCapab -> DataReqModeFlags */
            DataReqModeFlags drmf = new DataReqModeFlags();

            /* Set PhdAssociationInformation -> DataReqModeCapab -> DataReqModeFlags (0) "single response" */
            byte[] drmfb = {(byte) 128, (byte) 0};
            drmf.setValue(new BitString(drmfb));

            /* Set PhdAssociationInformation -> DataReqModeCapab */
            drmc.setData_req_mode_flags(drmf);
            drmc.setData_req_init_agent_count(1);
            drmc.setData_req_init_manager_count(0);

            /* Make PhAssociationInformation -> AttributeList */
            AttributeList al = new AttributeList();

            /* Set PhdAssociationInformation -> AttributeList */
            al.initValue();

            /* Set PhdAssociationInformation  */
            pai.setProtocol_version(pv);
            pai.setEncoding_rules(er);
            pai.setNomenclature_version(nv);
            pai.setFunctional_units(fn);
            pai.setSystem_type(st);
            pai.setSystem_id(sysId);
            pai.setDev_config_id(cid);
            pai.setData_req_mode_capab(drmc);
            pai.setOption_list(al);
            /* End Set PhdAssociationInformation */

            /* Encode PhdAssociationInformation */
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            IEncoder<PhdAssociationInformation> encoderPHD = CoderFactory.getInstance().newEncoder(ENCODING_RULE);
            encoderPHD.encode(pai, out);
            /*End encode PhdAssociationInformation*/

            /* Transform Bytes to Array*/
            /* Set DataProto ApduType -> AarqApdu -> DataProtoList -> DataProto */
            dp.setData_proto_id(dpi);
            /* Set ApduType -> AarqApdu -> DataProtoList -> DataProto -> DataProtoId */
            /* Transform Encode PhdAssociationInformation to Bytes Array*/
            dp.setData_proto_info(out.toByteArray()); //Get bytes for PhdAssociationInformation
			/* Add DataProto to ApduType -> AarqApdu -> DataProtoList */
            dpl.add(dp);
            /* Add AssociationVersion to ApduType -> AarqApdu -> AssociationVersion*/
            aarq.setAssoc_version(av);
            /* Add DataProtoList to ApduType -> AarqApdu ->DataProtoList */
            aarq.setData_proto_list(dpl);
            /* Add ArqApdu to ApduType -> AarqApdu */
            at.selectAarq(aarq);


            System.out.println("Connecting to Server...");
            socket = new Socket(host, port);


            (new SenderThread(socket)).start();
            Thread.sleep(100);
            SenderThread.sendApdu(at);

            Thread.sleep(1000);
            receive();

        } catch (Exception e) {
            System.out.println("Error: createAssociationRequest");
            e.printStackTrace();
        }


    }

    public void receive() {
        try {
            IDecoder decoder = CoderFactory.getInstance().newDecoder(ENCODING_RULE);
            while (true) {
                checkSocketState();
                ApduType rapdu = decoder.decode(socket.getInputStream(), ApduType.class);
                evalueResponse(rapdu);
            }


        } catch (Exception e) {
            System.out.println("Disconnected");
            /*e.printStackTrace()*/
            System.exit(0);
        }
    }
    static final byte[] HEX_CHAR_TABLE = {
        (byte) '0', (byte) '1', (byte) '2', (byte) '3',
        (byte) '4', (byte) '5', (byte) '6', (byte) '7',
        (byte) '8', (byte) '9', (byte) 'a', (byte) 'b',
        (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f'
    };

    public static String getHexString(byte[] raw)
            throws UnsupportedEncodingException {
        byte[] hex = new byte[2 * raw.length];
        int index = 0;

        for (byte b : raw) {
            int v = b & 0xFF;
            hex[index++] = HEX_CHAR_TABLE[v >>> 4];
            hex[index++] = HEX_CHAR_TABLE[v & 0xF];
        }
        return new String(hex, "ASCII");
    }

    public static String stringToHex(String base) {
        StringBuffer buffer = new StringBuffer();
        int intValue;
        for (int x = 0; x < base.length(); x++) {
            int cursor = 0;
            intValue = base.charAt(x);
            String binaryChar = new String(Integer.toBinaryString(base.charAt(x)));
            for (int i = 0; i < binaryChar.length(); i++) {
                if (binaryChar.charAt(i) == '1') {
                    cursor += 1;
                }
            }
            if ((cursor % 2) > 0) {
                intValue += 128;
            }
            buffer.append(Integer.toHexString(intValue) + " ");
        }
        return buffer.toString();
    }
}