<template>
    <v-container>
      <h2 class="text-h5">All Couriers</h2>
      
      <!-- Buton pentru reîmprospătarea listei -->
      <v-btn color="primary" @click="fetchCouriers">Refresh</v-btn>
      
      <!-- Tabel pentru afișarea curierilor -->
      <v-data-table
        :headers="headers"
        :items="couriers"
        item-value="id"
        class="mt-4"
        dense
        outlined
      >
        <template v-slot:[`item.manager_id`]="{ item }">
          <span>{{ item.manager_id || 'N/A' }}</span>
        </template>
      </v-data-table>
    </v-container>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'ViewAllCouriers',
    data() {
      return {
        couriers: [], // Lista curierilor
        headers: [ // Coloanele tabelului
          { text: 'ID', value: 'id' },
          { text: 'Name', value: 'name' },
          { text: 'Email', value: 'email' },
          { text: 'Manager ID', value: 'manager_id' }
        ]
      };
    },
    methods: {
      async fetchCouriers() {
        try {
          // Obține lista tuturor curierilor
          const response = await axios.get('http://localhost:8080/courier');
          this.couriers = response.data;
        } catch (error) {
          console.error("Error fetching couriers:", error);
        }
      }
    },
    mounted() {
      this.fetchCouriers(); // Încarcă lista curierilor la montarea componentei
    }
  };
  </script>
  
  <style scoped>
  .mt-4 {
    margin-top: 16px;
  }
  </style>
  