<template>
<div  v-for="or in datas">
     <ProcessCard :orderDetails="or"/>
</div>
</template>
<script setup lang="ts">

import   ProcessCard  from "@/components/ProcessCard.vue"
   
   import axios from 'axios';
import { onMounted, ref } from 'vue'

  
 
   export interface OrderDetails{
    orderid : string,
    customername : string,
    price :number,
    status :string,
    orderitems: 
        {
            foodname :string,
            qty : number
        }
   }

   const datas=ref<OrderDetails[]>([]);

async function fetchData()
{

    try{
        const orderdata=await axios.get("http://localhost:8082/api/getall");
        datas.value=orderdata.data;
    }
    catch(e)
    {
        // console.log(e);
    }
}
  onMounted(async () => {
 
  await fetchData(); 
  
})
</script>
<style>

</style>