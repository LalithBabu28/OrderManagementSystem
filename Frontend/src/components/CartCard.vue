<template>
    
    <div class="Item">
           <img :src="props.productimage" height="100px">
            <div class="inside"> 
            <p>   {{  props.productname }} </p>
           <button class="btn-qty"  @click="increment"> + </button>
           <p>{{  countqty }}</p>
           <button  class="btn-qty" @click="decrement">  - </button>
            <p> $ {{ Totalprice  }}</p>
        </div>
    </div>
  
</template>
<script setup lang="ts">
     import { prostore, type orderdetails } from '@/stores/Piniastore';
     import  { computed, onMounted, ref, watch }  from 'vue';

     
    const st=prostore();
    const props=defineProps({
        productid:String,
        productname:String,
        productimage:String,
        productprice:Number,
        placeorder:Boolean
    })

    onMounted(()=>{
        addorderpinia();
        emit("caltotal",
            props.productprice);
    })
    
    const emit=defineEmits(['caltotal'])

    const countqty=ref(1)

    function increment()
    {
        countqty.value++;
        if(props.productid){
        st.setqty(props.productid,countqty.value);
        }
        emit("caltotal",props.productprice)
    }

    function decrement()
    {
        if(countqty.value > 1){
            emit("caltotal", props.productprice ? -Number(props.productprice) : 0);
            countqty.value--;
        }  
    }

   const Totalprice=computed(() =>{
    const price=props.productprice ?? 0;
        return countqty.value * price;
    })

    function addorderpinia()
    {
        if(props.productid){
        const order : orderdetails ={
            productid:props.productid,
            qty:1
        }
        st.addorder(order);   
    }
    }    


  
</script>


<style>
.Item{
    display: flex;
    column-gap: 20px;
    border: 2px solid rgb(41, 136, 224);
    width: 600px;
    
}

.btn-qty{
    height: 30px;
    border-radius: 40px;
    border: none;
    color: white;
    background-color: rgb(43, 99, 204);
    font-size: 24px;
}

.inside{
    display: flex;
    column-gap: 60px;
    padding: 20px
}

p{
    font-size: 20px;    
    color: rgb(18, 82, 199);
    justify-items:center;
    margin-bottom: 10px
}

</style>