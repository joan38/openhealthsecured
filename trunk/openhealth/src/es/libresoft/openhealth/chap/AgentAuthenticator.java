/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.libresoft.openhealth.chap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author joan
 */
public class AgentAuthenticator {
    
    private byte[] key;

    public AgentAuthenticator(byte[] key) {
        this.key = key;
    }
    
    /**
     * Resolve the challenge.
     * Concat the key + the chalenge and hash it.
     * 
     * @param challenge
     * @return 
     */
    public byte[] resolveTheChallenge(byte[] challenge) {
        try {
            MessageDigest hasher = MessageDigest.getInstance("MD5");
            
            // Concat the secret with the challenge
            byte[] c = new byte[key.length + challenge.length];
            System.arraycopy(key, 0, c, 0, key.length);
            System.arraycopy(challenge, 0, c, key.length, challenge.length);
            
            return hasher.digest(c);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
}
