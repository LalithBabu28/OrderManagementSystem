import { createApp } from 'vue'
import App from './App.vue'
import { route } from './router/routes'
import { createPinia } from 'pinia'

const pinia=createPinia();
createApp(App).use(pinia).use(route).mount('#app')
