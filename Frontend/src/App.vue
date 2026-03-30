<script setup lang="ts">
import axios from 'axios';
import type { ProductDetails } from './Pages/ProductLists.vue';
import { onMounted, ref, computed } from 'vue';
import { prostore } from './stores/Piniastore';

const st=prostore();
const datas = ref<ProductDetails[]>([]);
const searchQuery = ref("");

async function getall() {
  try {
    const res = await axios.get("http://localhost:8082/api/getbymatch",{ params : {
        field : searchQuery
    }});
    datas.value = res.data;
  } catch (e) {
    console.error(e);
  }
}

const filteredProducts = computed(() => {
  if (!searchQuery.value) return [];
  return datas.value.filter(product =>
    product.productname.toLowerCase().includes(searchQuery.value.toLowerCase())
  );
});

computed(() => {
  getall();
});

const count=computed(() => {
    return st.getProductscount();
   })

</script>

<template>
  <div>
    <div class="searchBar">
      <p class="titles">RestaurantWare</p>
      <div class="search-container">
        <input 
          v-model="searchQuery" 
          class="searchbox" 
          type="search" 
          placeholder="Search Products"
        >
        <ul v-show="filteredProducts.length > 0" class="dropdown">
          <li v-for="value in filteredProducts" :key="value.productname">
            {{ value.productname }}
          </li>
        </ul>
      </div>
       <router-link to="/addproduct" class="button-link">Add product </router-link> &nbsp;
      <router-link to="/product" class="button-link"> Get All Products</router-link> &nbsp;
      <router-link to="/orderprocessing" class="button-link">Order Processing</router-link>
      <router-link to="/CartPage"  class="button-link">Go To Cart : {{  count }}</router-link>

    </div>
    <br>
    <router-view />
  </div>
</template>

<style scoped>
.searchBar {
  border: 2px solid rgb(36, 198, 238);
  background-color: #2fb6f5;
  display: flex;
  column-gap: 10px;
  padding: 10px 20px;
}

.search-container {
  position: relative;
  align-self: center;
}

.searchbox {
  height: 50px;
  width: 600px;
  border: none;
  font-size: 20px;
  padding: 10px;
  box-sizing: border-box;
}

.titles {
  font-size: 30px;
  color: rgb(13, 111, 177);
  margin: 0;
  align-self: center;
}

.dropdown {
  list-style: none;
  margin: 0;
  padding: 0;
  z-index: 10;
  position: absolute;
  width: 100%;
  background-color: white;
  border: 1px solid #ccc;
  max-height: 300px;
  overflow-y: auto;
  box-shadow: 0px 4px 6px rgba(0,0,0,0.1);
}

/* Shows dropdown when input is focused */
.searchbox:focus + .dropdown {
  display: block;
}

.dropdown li {
  padding: 12px;
  cursor: pointer;
  border-bottom: 1px solid #eee;
  color: black;
}

.dropdown li:hover {
  background-color: #f0f0f0;
}

.button-link{
    padding-top: 30px;
    text-decoration: none;
    color: rgb(248, 247, 247);
    background-color: rgb(20, 77, 184);
    padding: 10px 20px;
    border-radius: 30px;
}

</style>