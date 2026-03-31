import { defineStore } from "pinia";
import type { ProductDetails } from "@/Pages/ProductLists.vue";
import {ref } from 'vue';


export interface orderdetails{
   productid:string,
   qty:number
}
export const  prostore=defineStore("prod",() => {
    const product=ref<ProductDetails[]>([])
    const orders=ref<orderdetails[]>([]);
    
        function addpro(pro : ProductDetails)
        {  
            const exist=product.value.some((p) => p.productid === pro.productid);
            console.log(pro.productid);

            if(exist===false){
            product.value.push(pro);
            }
            else
            {
                console.log("already exist");
            }
            
        }

        function addorder(order : orderdetails )
        {
            const exists=orders.value.some((o) => o.productid === order.productid);
            if(!exists)
            {
                orders.value.push(order);
            }
        }

        function getProductscount()
        {
            return product.value.length;
        }

        function setqty(productid:string,qty:number)
        {
            orders.value.forEach((p) => { if(p.productid == productid) {p.qty=qty}});
        }

        function removeorder(productid:string)
        {
            orders.value=orders.value.filter((o) => o.productid !== productid);
            
        }

        return { product ,setqty,orders,addorder,addpro ,getProductscount}
    })
    