<template>
    <v-container>
      <h2 class="text-h5">Available packages</h2>
      <v-btn text="refresh" @click="fetchPackages"></v-btn>
      <v-data-table
      
        :items="packages" 
      >
      </v-data-table>
    </v-container>
  </template>
  
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'PackagesView',
    data() {
      return {
        packages: []
      };
    },
    methods: {
      async fetchPackages() {
        try {
          const response = await axios.get('http://localhost:8080/package');
          this.packages = response.data;
        } catch (error) {
          console.error("Error fetching packages:", error);
        }
      }
    },
    mounted(){
      this.fetchPackages();
    }

  };
  </script>
  
  <style scoped>
  .packageClass {
    margin-bottom: 10px;
  }
  </style>