<template>
    <v-container>
      <h2 class="text-h5">Available packages</h2>
      <v-btn text="refresh" @click="fetchPackages"></v-btn>
      <v-data-table :items="packages">
        <template v-slot:[`item.courier`]="{ item }">
          <v-select
            v-if="!item.courier"
            :items="couriers"
            item-title="name"
            item-value="id"
            v-model="item.selectedCourier"
            label="Select courier"
          ></v-select>
          <span v-else>{{ item.courier.name }}</span>
  
          <!-- Adaugă butonul pentru asignare -->
          <v-btn
            v-if="item.selectedCourier"
            color="primary"
            @click="assignCourier(item)"
          >
            Assign Courier
          </v-btn>
        </template>
      </v-data-table>
    </v-container>
  </template>
  
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'PackagesView',
    data() {
      return {
        packages: [],
        couriers: [] // Lista curierilor
      };
    },
    methods: {
  async fetchPackages() {
    try {
      const response = await axios.get('http://localhost:8080/package/NoCourier');
      this.packages = response.data;
      this.fetchCouriers();
    } catch (error) {
      console.error('Error fetching packages:', error);
    }
  },
  async fetchCouriers() {
    try {
      const response = await axios.get('http://localhost:8080/courier');
      this.couriers = response.data;
      console.log('Curierii:', this.couriers);
    } catch (error) {
      console.error('Error fetching couriers:', error);
    }
  },
  async assignCourier(item) {
  try {
    const response = await axios.put(
      `http://localhost:8080/package/AssignCourier/${item.id}/${item.selectedCourier}`
    );

    console.log("Courier assigned:", response.data);
    console.log("Selected Package ID:", item.id);
    console.log("Selected Courier ID:", item.selectedCourier);

    this.fetchPackages(); // Refreshează lista de pachete
  } catch (error) {
    console.error("Error assigning courier:", error);
  }
}






},

    mounted() {
      this.fetchPackages(); 
      this.fetchCouriers(); // Încarcă pachetele și curierii la montarea componentei
    }
  };
  </script>
  
  <style scoped>
  .packageClass {
    margin-bottom: 10px;
  }
  </style>
  