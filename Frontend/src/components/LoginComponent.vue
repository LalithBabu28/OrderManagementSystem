<script setup>
import { GoogleSignInButton } from "vue3-google-signin";

const handleSuccess = async (response) => {
  const { credential } = response;
  
 
  const result = await fetch("http://localhost:8082/api/auth/google", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ token: credential })
  });
  
  if (result.ok) {
    console.log("Logged in successfully!");
  }
};
</script>

<template>
  <GoogleSignInButton @success="handleSuccess" @error="handleError" />
</template>