import App from "@/App.vue";
import AddProduct from "@/Pages/AddProduct.vue";
import CartComponent from "@/Pages/CartComponent.vue";
import HomePage from "@/Pages/HomePage.vue";
import OrderProcessing from "@/Pages/OrderProcessing.vue";
import ProductLists from "@/Pages/ProductLists.vue";
import { createRouter, createWebHistory } from "vue-router";

export const route=createRouter({
    history:createWebHistory(),
    routes:[{
        path:'/',
        name:'Home',
        component:HomePage
    },{
        path:'/product',
        name:'listproduct',
        component:ProductLists
    },
     {
        path:'/Addproduct',
        name:'addproduct',
        component:AddProduct
     },{
        path:'/CartPage',
        name:'cartpage',
        component:CartComponent
     },
    {
        path:'/orderprocessing',
        name:'Orderprocessing',
        component:OrderProcessing
    }]
})