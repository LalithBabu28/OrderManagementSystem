<script setup lang="ts">
import axios from 'axios';
import type { ProductDetails } from './Pages/ProductLists.vue';
import { ref, computed } from 'vue';
import { prostore } from './stores/Piniastore';
// 1. Import the Google Login component
import { GoogleSignInButton, type CredentialResponse } from "vue3-google-signin";

const st = prostore();
const datas = ref<ProductDetails[]>([]);
const searchQuery = ref("");

// 2. Track login status
const isLoggedIn = ref(false);

async function getall() {
  try {
    const res = await axios.get("http://localhost:8082/api/getbymatch", {
      params: { field: searchQuery.value } // Fixed: .value needed for refs
    });
    datas.value = res.data;
  } catch (e) {
    console.error(e);
  }
}


const handleLoginSuccess = async (response: CredentialResponse) => {
  const { credential } = response;

  try {
    const result = await axios.post("http://localhost:8082/api/auth/google", {
      token: credential
    });

    if (result.status === 200) {
      isLoggedIn.value = true;
      console.log("Authenticated successfully");
    }
  } catch (error) {
    console.error("Login failed", error);
    alert("Backend authentication failed.");
  }
};

const handleLoginError = () => {
  console.error("Google Sign-In failed");
};

const filteredProducts = computed(() => {
  if (!searchQuery.value) return [];
  return datas.value.filter(product =>
    product.productname.toLowerCase().includes(searchQuery.value.toLowerCase())
  );
});

const count = computed(() => {
  return st.getProductscount();
});
</script>

<template>
  <div>
    <div v-if="!isLoggedIn" class="login-screen">
      <div class="login-card">
        <h2>RestaurantWare</h2>
        <p>Please sign in to manage products</p>
        <GoogleSignInButton @success="handleLoginSuccess" @error="handleLoginError" />
      </div>
    </div>

    <div v-else>
      <div class="searchBar">
        <p class="titles">RestaurantWare</p>
        <div class="search-container">
          <input 
            v-model="searchQuery" 
            class="searchbox" 
            type="search" 
            placeholder="Search Products"
            @input="getall"
          >
          <ul v-show="filteredProducts.length > 0" class="dropdown">
            <li v-for="value in filteredProducts" :key="value.productname">
              {{ value.productname }}
            </li>
          </ul>
        </div>
        <router-link to="/addproduct" class="button-link">Add product</router-link> &nbsp;
        <router-link to="/product" class="button-link">Get All Products</router-link> &nbsp;
        <router-link to="/orderprocessing" class="button-link">Order Processing</router-link>
        <router-link to="/CartPage" class="button-link">Go To Cart : {{ count }}</router-link>
        
        <button @click="isLoggedIn = false" class="logout-btn">Logout</button>
      </div>
      <br>
      <router-view />
    </div>
  </div>
</template>

<style scoped>


.login-screen {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
}

.login-card {
  background: white;
  padding: 40px;
  border-radius: 12px;
  box-shadow: 0 4px 15px rgba(0,0,0,0.1);
  text-align: center;
}

.logout-btn {
  background: #ff4d4d;
  color: white;
  border: none;
  padding: 5px 15px;
  border-radius: 5px;
  cursor: pointer;
  align-self: center;
  margin-left: 10px;
}

/* Rest of your searchBar styles */
.searchBar {
  border: 2px solid rgb(36, 198, 238);
  background-color: #2fb6f5;
  display: flex;
  column-gap: 10px;
  padding: 10px 20px;
}
/* ... etc ... */
</style>