<template>
    <v-container>
      <!-- Formular pentru adresa curierului -->
      <v-text-field
        label="Courier Name"
        v-model="currentCourier.name"
        prepend-inner-icon="mdi-account"
        outlined
        :rules="[value => !!value || 'Courier name is required!']"
      ></v-text-field>
      <v-text-field
        label="Email"
        v-model="currentCourier.email"
        prepend-inner-icon="mdi-email"
        outlined
        :rules="[value => !!value || 'Courier email is required!']"
      ></v-text-field>
      <v-text-field
        label="Manager ID"
        v-model="currentCourier.manager_id"
        prepend-inner-icon="mdi-account"
        outlined
      ></v-text-field>
  
      <!-- Buton pentru trimiterea datelor -->
      <v-btn color="primary" @click="createCourier">Create Courier</v-btn>
  
      <!-- Mesaj de succes -->
      <v-snackbar v-model="snackbar" color="success" top>
        Courier has been created successfully!
        <v-btn color="white" @click="snackbar = false">Close</v-btn>
      </v-snackbar>
    </v-container>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'AddCourier',
    data() {
      return {
        currentCourier: {
          name: "",
          email: "",
          manager_id: null
        },
        snackbar: false // Controlează vizibilitatea mesajului de succes
      };
    },
    methods: {
      async createCourier() {
        try {
          if (!this.currentCourier.name || !this.currentCourier.email) {
            this.$emit("error", "Name and email are required!");
            return;
          }
  
          // Trimite datele curierului la server
          const response = await axios.post('http://localhost:8080/courier', this.currentCourier);
          console.log("Courier created successfully:", response.data);
  
          // Afișează mesajul de succes
          this.snackbar = true;
  
          // Resetează formularul
          this.currentCourier.name = "";
          this.currentCourier.email = "";
          this.currentCourier.manager_id = null;
  
          // Închide snackbar-ul automat după 3 secunde (opțional)
          setTimeout(() => {
            this.snackbar = false;
          }, 3000); // 3000 ms = 3 secunde
        } catch (error) {
          console.error("Error creating courier:", error);
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .package-form {
    margin: 20px 0;
  }
  </style>
  