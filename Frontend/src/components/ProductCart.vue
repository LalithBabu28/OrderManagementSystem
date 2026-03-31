<script setup lang="ts">
import { ref } from 'vue'
    
    const emit=defineEmits(['add-to-cart'])
    const porp=defineProps({
        productid:String,
        productname:String,
        price:Number,
        myimage:String,
        qty:Number
    })

    
   function additem(porp : any)
   {
    emit("add-to-cart",{productid : porp.productid
        ,productname : porp.productname 
        ,Image : porp.myimage
        ,price : porp.price,
         qty : porp.qty});
   }
   

   
</script>

<template>
  
  <div>
      <div class="img">
       <p>RestaurantWare</p>
       <img :src=" porp.myimage" alt="productimg" height="100" width="100">
       <p>{{ porp.productid }}</p>
       <p> {{  porp.productname  }}</p>
       <p> ${{  porp.price  }}   &nbsp; Qty : {{  porp.qty  }} </p>
       <button class="btn" @click="additem(porp)">Add to Cart</button>
      </div>
      
  </div>
</template>

<style scoped>
    .img{
        display: block;
        position: relative;
        border: 2px solid rgb(76, 151, 221);
        padding-left: 10px;
        padding-bottom: 10px;
        width: 200px;
        height: 330px;
    }

    .btn{
        width: 180px;
        height: 40px;
        border-radius: 20px;
        color: white;
        background-color: rgb(17, 92, 179);
        border: none;
        font-size: medium;
        font-weight: 200;
        cursor: pointer;
    }

    .btn:hover{
        background-color: rgb(11, 75, 148);
    }
    p{
        color: rgb(6, 59, 139);
        font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif
    }
</style>
 