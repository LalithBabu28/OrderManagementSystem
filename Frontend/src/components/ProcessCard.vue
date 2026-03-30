<template>
  <div   class="acitve" >
      <h4>{{ prop.orderDetails.orderid }}</h4>
      <p>{{  prop.orderDetails.customername }}</p>
      <p> </p>
      <p></p>
      <p> Status : {{ prop.orderDetails.status }} </p>
      <p>Price : {{    }}</p>
  </div>
</template>

<script setup lang="ts">
import type { OrderDetails } from '@/Pages/OrderProcessing.vue';
import { PropModification } from 'dexie';
import { computed } from 'vue';


const prop=defineProps<{
    orderDetails:OrderDetails
}>();


   function printorderitems()
   {
      console.log("")
   }

   defineExpose({ })


</script>

<style>


</style>