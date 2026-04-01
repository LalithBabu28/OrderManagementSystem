<template>
    <div class="Item">
        <img :src="props.productimage" height="100px" alt="product">
        <div class="inside"> 
            <p>{{ props.productname }}</p>
            <button class="btn-qty" @click="increment">+</button>
            <p>{{ countqty }}</p> 
            <button class="btn-qty" @click="decrement">-</button>
            <p>$ {{ Totalprice }}</p>
            <button class="remove" @click="removeItem">Remove</button>
        </div>
    </div>
</template>

<script setup lang="ts">
import { prostore, type orderdetails } from '@/stores/Piniastore';
import { computed, onMounted, ref } from 'vue';

const st = prostore();

const props = defineProps({
    productid: String,
    productname: String,
    productimage: String,
    productprice: Number,
    placeorder: Boolean
})

// Added 'remove' to emits so the parent knows to remove this from the list
const emit = defineEmits(['caltotal', 'remove'])

onMounted(() => {
    addorderpinia();
    emit("caltotal", props.productprice ?? 0);
})

const countqty = ref(1)

function increment() {
    countqty.value++;
    if (props.productid) {
        st.setqty(props.productid, countqty.value);
    }
    emit("caltotal", props.productprice ?? 0)
}

function decrement() {
    if (countqty.value > 1) {
  
        emit("caltotal", props.productprice ? -Number(props.productprice) : 0);
        countqty.value--;
        if (props.productid) {
            st.setqty(props.productid, countqty.value);
        }
    }  
}

const Totalprice = computed(() => {
    const price = props.productprice ?? 0;
    return countqty.value * price;
})

function addorderpinia() {
    if (props.productid) {
        const order: orderdetails = {
            productid: props.productid,
            qty: 1
        }
        st.addorder(order);   
    }
}    

function removeItem() {  
    emit("caltotal", -Totalprice.value);
    st.removeorder(props.productid ?? "");
    emit("remove", props.productid);
}
</script>

<style scoped>
.Item {
    display: flex;
    column-gap: 20px;
    border: 2px solid rgb(41, 136, 224);
    width: 800px;
    margin-bottom: 15px;
}

.btn-qty {
    height: 30px;
    width: 35px;
    border-radius: 40px;
    border: none;
    color: white;
    background-color: rgb(43, 99, 204);
    font-size: 24px;
    cursor: pointer;
}

.inside {
    display: flex;
    align-items: center; /* Vertically center content */
    column-gap: 60px;
    padding: 20px;
}

p {
    font-size: 20px;    
    color: rgb(18, 82, 199);
    margin: 0; /* Cleaned up margins */
}

.remove {
    height: 35px;
    width: 100px;
    border-radius: 40px;
    border: none;
    color: white;
    background-color: rgb(255, 0, 0);
    font-size: medium;
    cursor: pointer;
}

.remove:hover {
    background-color: rgb(204, 0, 0);
}
</style>