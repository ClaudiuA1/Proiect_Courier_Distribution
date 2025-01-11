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
          <div v-if="tab === 'one'" class="home-tab" :style="{ backgroundImage: `url(${imagineHome})` }">
            <h1 class="home-title">Welcome to Package delivery App</h1>
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
import imagineHome from '@/assets/Imagine_Home.jpg'

export default {
  name: "App",
  components: {
    PackagesView,
    AddPackage,
    AssignCourier,
    AddCourier,
    CouriersView,

  },
  data: () => ({
    tab: "one", // Valoarea implicită a tab-ului activ
    username: "Nume utilizator",
    imagineHome
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
.home-tab {
  width: 100%; /* Ocupă lățimea completă */
  height: calc(100vh); /* Înălțime completă, minus bara de navigare (ajustează în funcție de dimensiunea acesteia) */
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}


.home-title {
  color: white;
  /* Text alb pentru contrast */
  font-size: 3rem;
  /* Dimensiune mare pentru text */
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
  /* Adaugă un efect de umbră textului */
  position: top;
  
}
</style>
