<template>
    <h1>Cart Items</h1>
    <div v-for="value in CartItems" :key="value.productid">
        <CartCard  ref="childcall"   :productid="value.productid" :productname="value.productname" :productimage="value.Image" :productprice="value.price" @caltotal="handleGrandTotal" /><br>
    </div>

    <p> Total Amount : $ {{  grandtotal }}</p>
    <button @click="placeorder" class="button-link">Place Order</button>
    
    <div v-if="visiblediv" class="orderdiv">
             <button class="tick">✓</button>
             <h4>Order Placed</h4>
    </div>
</template>

<script  setup lang="ts">
    
    import { prostore } from '@/stores/Piniastore';
    import  CartCard  from '@/components/CartCard.vue'
    import { computed, ref, watch  } from 'vue';
    import { type ProductDetails } from './ProductLists.vue';
    import axios from 'axios';

    const st=prostore(); 
    const CartItems=ref<ProductDetails[]>([]);
    CartItems.value=[...st.product];
    
    const grandtotal = ref<number>(0);
    const visiblediv=ref(false)
    function handleGrandTotal(e:number){
      grandtotal.value=grandtotal.value+e;
     }

    async function placeorder()
     {
        console.log(st.orders);
        try{
            const res=await axios.put("http://localhost:8082/api/addorders",st.orders)
     }
     catch(e)
     {
        console.error(e);
     }
     visiblediv.value=true;
    }
    
    watch(visiblediv,() =>{
        if(visiblediv)
    {
        setTimeout(() =>{
            visiblediv.value = false
        },3000);
    }
    })
   
</script>

<style>
.button-link{
    padding-top: 30px;
    text-decoration: none;
    color: rgb(243, 247, 248);
    background-color: rgb(34, 73, 145);
    padding: 10px 20px;
    border-radius: 30px;
}

.orderdiv{
    position:fixed;
   overflow-wrap: normal;
   color: rgb(5, 36, 136);
   font-size: larger;
    background-color: rgb(226, 230, 235);
    z-index: 1001;
    border-radius: 30px;
    width: 500px;
    height: 300px;
    top: 40%;
    left: 30%;
    font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
    border-radius: 30px;
    
}

.orderdiv h4{
    margin-left: 35%;
} 

.tick{
    height: 80px;
    width: 80px;
    border-radius: 50px;
    border-style: none;
    font-size: 60px;
    color: rgb(255, 255, 255);
    justify-items: center;
    background-color: rgb(14, 66, 163);
    margin-left: 38%;
    margin-top: 18%;
}
</style>