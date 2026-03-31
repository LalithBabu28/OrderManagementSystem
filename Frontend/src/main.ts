import { createApp } from 'vue'
import App from './App.vue'
import { route } from './router/routes'
import { createPinia } from 'pinia'
import vue3GoogleSignIn from 'vue3-google-signin';

const pinia=createPinia();


createApp(App)
     .use(vue3GoogleSignIn, {clientId: '1005373586835-7v909a4q73eqt5ff9f4rtfaum2a0i442.apps.googleusercontent.com'})
     .use(pinia)
     .use(route)
     .mount('#app')
