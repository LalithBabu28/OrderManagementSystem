<template>
    <h1>Cart Items</h1>
    <div v-if="CartItems.length === 0" class="emptycart">
        <p>Your cart is empty.</p>

    </div>
 <div v-else v-for="value in CartItems" :key="value.productid" class="cartitems">
    <CartCard 
        :productid="value.productid" 
        :productname="value.productname" 
        :productimage="value.Image" 
        :productprice="value.price" 
        @caltotal="handleGrandTotal"
        @remove="handleRemoveItem" 
    />
    <br>
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
    import {  ref, watch  } from 'vue';
    import { type ProductDetails } from './ProductLists.vue';
    import axios from 'axios';

    const st=prostore(); 
    const CartItems=ref<ProductDetails[]>([]);
    CartItems.value=[...st.product];
    
    const grandtotal = ref<number>(0);
    const visiblediv=ref(false)
    const removeitems=ref(false);

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
            visiblediv.value = false,
            removeitems.value=true;
        },3000);
    }
    })

    watch(removeitems,() =>{
        if(removeitems)
    {
         st.removeallorders();
     st.removeallproduct();
     CartItems.value = [];
     grandtotal.value=0;
    }
    })


function handleRemoveItem(id: string) {
  
    CartItems.value = CartItems.value.filter(item => item.productid !== id);
    
   
    st.product = st.product.filter(p => p.productid !== id);
}
   
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