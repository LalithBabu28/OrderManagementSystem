<script setup lang="ts">
    import ProductCart from '@/components/ProductCart.vue';
    import {onMounted, ref} from 'vue'
    import axios from 'axios';
    import { prostore } from '@/stores/Piniastore';

   
    export interface ProductDetails{
      productid:string,
      productname:string,
      price:number,
      Image:string,
      qty:number
    }

     export interface proorder{
    productid:string,
    qty:number
   }

    const pro=ref<ProductDetails[]>()
    

    async function fetchdata()
    {
      try{
      const respones=await axios.get("http://localhost:8082/api/GetElastic");
      pro.value=respones.data;
      }
      catch(e)
      {
        console.error(e);
      }
    }
   
   onMounted(()=>{
      fetchdata();
   }
   )

   const st=prostore();

   function additemcart(pro : any)
   {   
      st.addpro(pro)
   }

    
</script>

<template>
    <div class="main">
      <div v-for="product in pro" :key="product.productid">
            <ProductCart :productid="product.productid" :productname="product.productname" :price="product.price" :myimage="product.Image" :qty="product.qty" @add-to-cart="additemcart" />
      </div>
      
      

    </div>   
    <br><br>
</template>

<style scoped>

    .main{
      display: flex;
      flex-wrap: wrap;
      row-gap: 30px;
      column-gap: 20px;
    }
</style>
