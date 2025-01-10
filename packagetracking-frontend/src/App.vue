<template>
  <v-app>
    <v-main>
      <v-card>
        <!-- Tabs -->
        <v-tabs v-model="tab" bg-color="deep-purple-accent-4" align-tabs="center" color="black">
          <v-tab value="one">Home</v-tab>
          <v-tab value="two">View Packages</v-tab>
          <v-tab value="three">Add Package</v-tab>
          <v-tab value="four">Assign Courier</v-tab>
          <v-tab value="five">Add Courier</v-tab>
          <v-tab value="six">View Couriers</v-tab>
        </v-tabs>

        <!-- Conținutul bazat pe tab-ul selectat -->
        <v-card-text>
          <!-- Home Tab -->
          <div v-if="tab === 'one'">
            <h1>Welcome to Home</h1>
            <img src="@/assets/Imagine_Home.jpg" alt="Welcome Image" class="home-image" />
          </div>


          <!-- View Packages Tab -->
          <div v-if="tab === 'two'">
            <h2>View all packages</h2>
            <packages-view />
          </div>

          <!-- Add Package Tab -->
          <div v-if="tab === 'three'">
            <h2>Add a new package</h2>
            <add-package :currentUser="username" ref="addPostDialog" />
          </div>

          <!-- Add Courier to Package Tab -->
          <div v-if="tab === 'four'">
            <h2>Assign Courier to Package</h2>
            <!-- Poți include o componentă aici -->
            <assign-courier />
          </div>
          <div v-if="tab === 'five'">
            <h2>Add Courier</h2>
            <!-- Poți include o componentă aici -->
            <add-courier />

          </div>
          <div v-if="tab === 'six'">
            <h2>View all Couriers</h2>
            <!-- Poți include o componentă aici -->
            <couriers-view />

          </div>
        </v-card-text>
      </v-card>
    </v-main>
  </v-app>
</template>

<script>
import PackagesView from "./components/PackagesView.vue";
import AddPackage from "./components/AddPackage.vue";
import AssignCourier from "./components/AssignCourier.vue";
import AddCourier from "./components/AddCourier.vue";
import CouriersView from "./components/CouriersView.vue";

export default {
  name: "App",
  components: {
    PackagesView,
    AddPackage,
    AssignCourier,
    AddCourier,
    CouriersView
  },
  data: () => ({
    tab: "one", // Valoarea implicită a tab-ului activ
    username: "Nume utilizator",
  }),
  mounted() {
    this.$refs.addPostDialog && (this.$refs.addPostDialog.showDialog = false);
  },
  methods: {
    openDialog() {
      this.$refs.addPostDialog.showDialog = true;
    },
  },
};
</script>

<style scoped>
.home-image {
  display: block;
  margin: 20px auto; /* Adaugă spațiu în jurul imaginii, o centrează */
  max-width: 100%; /* Ajustează imaginea să se încadreze în container */
  height: auto; /* Păstrează proporțiile imaginii */
}
</style>

