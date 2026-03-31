package com.main.productmangement;

import java.util.Collections;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

@Service
public class GoogleAuthService {

    private final String clientId = "1005373586835-7v909a4q73eqt5ff9f4rtfaum2a0i442.apps.googleusercontent.com";

    public GoogleIdToken.Payload verifyToken(String idTokenString) throws Exception {
        System.out.println("Received Token for verification: " + idTokenString.substring(0, 10) + "...");
        
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(clientId))
                .build();

        try {
            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                return idToken.getPayload();
            } else {
                System.out.println("Verification returned null. Check Client ID and Token expiration.");
                throw new RuntimeException("Invalid ID token.");
            }
        } catch (Exception e) {
            System.out.println("Exception during verification: " + e.getMessage());
            throw e;
        }
    }
}