<template>
  <v-container>
    <!-- Formular pentru adresa de livrare și plata la livrare -->
    <v-text-field
      label="Delivery Address"
      v-model="currentPackage.deliveryAddress"
      prepend-inner-icon="mdi-map-marker"
      outlined
      :rules="[value => !!value || 'Delivery Address is required']"
    ></v-text-field>

    <v-checkbox
      label="Pay on Delivery"
      v-model="currentPackage.payOnDelivery"
      :true-value="true"
      :false-value="false"
      outlined
    ></v-checkbox>

    <!-- Buton pentru trimiterea datelor -->
    <v-btn color="primary" @click="createPackage">Create Package</v-btn>

    <!-- Mesaj de succes -->
    <v-snackbar v-model="snackbar" color="success" top>
      Pachetul a fost creat cu succes!
      <v-btn color="white" @click="snackbar = false">Închide</v-btn>
    </v-snackbar>
  </v-container>
</template>

<script>
import axios from 'axios';

export default {
  name: 'AddPackage',
  props: {
    currentUser: String
  },
  data() {
    return {
      currentPackage: {
        deliveryAddress: "",
        payOnDelivery: false,
        status: "PENDING" // Inițial, checkbox-ul nu este bifat
      },
      snackbar: false // Controlează vizibilitatea mesajului de succes
    };
  },
  methods: {
    async createPackage() {
      try {
        if (!this.currentPackage.deliveryAddress) {
        this.$emit("error", "Adresa de livrare este obligatorie!");
        return;
      }
        // Trimite datele la server
        const response = await axios.post('/package', this.currentPackage);
        console.log("Package created successfully:", response.data);
        
        // Afișează mesajul de succes
        this.snackbar = true;

        // Resetează formularul
        this.currentPackage.deliveryAddress = "";
        this.currentPackage.payOnDelivery = false;

        // Închide snackbar-ul automat după 3 secunde (opțional)
        setTimeout(() => {
          this.snackbar = false;
        }, 3000); // 3000 ms = 3 secunde
      } catch (error) {
        console.error("Error creating package:", error);
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
