/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.libresoft.openhealth.chap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author joan
 */
public class ManagerAuthenticator {

    private HashMap<String, byte[]> agents = new HashMap<String, byte[]>();

    public void addAgent(byte[] systemId, byte[] key) {
        agents.put(new String(systemId), key);
    }

    public void removeAgent(byte[] systemId) {
        agents.remove(new String(systemId));
    }

    /**
     * Autenticate the agent
     *
     * @param systemId
     * @param challengeResponse
     * @return
     */
    public boolean authenticateAgent(byte[] systemId, byte[] challengeResponse, byte[] challenge) {
        try {
            MessageDigest hasher = MessageDigest.getInstance("MD5");
            byte[] key = agents.get(new String(systemId));
            
            if (key == null) {
                throw new IllegalStateException("The key for the given system id doesn't exist");
            }

            // Concat the secret with the challenge
            byte[] c = new byte[key.length + challenge.length];
            System.arraycopy(key, 0, c, 0, key.length);
            System.arraycopy(challenge, 0, c, key.length, challenge.length);

            byte[] challengeRightResponse = hasher.digest(c);
            return Arrays.equals(challengeResponse, challengeRightResponse);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
