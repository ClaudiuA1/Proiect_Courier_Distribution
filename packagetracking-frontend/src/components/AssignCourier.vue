<template>
  <div class="bg-gray-100 min-h-screen">
    <div class="container mx-auto px-4 py-8">
      <!-- Header -->
      <header class="gradient-bg rounded-xl shadow-lg mb-8 p-6 text-white">
        <div class="flex flex-col md:flex-row md:items-center justify-between gap-4">
          <h2 class="text-2xl md:text-3xl font-bold">Assign Couriers</h2>
          <button
            @click="refreshData"
            :disabled="loading"
            class="bg-white text-purple-700 hover:bg-purple-50 px-6 py-2 rounded-lg font-medium flex items-center space-x-2 transition-colors duration-200"
          >
            <i :class="loading ? 'fas fa-spinner animate-spin' : 'fas fa-sync-alt'"></i>
            <span>{{ loading ? 'Refreshing...' : 'Refresh' }}</span>
          </button>
        </div>
      </header>

      <!-- Assignment Table -->
      <main class="bg-white rounded-xl shadow-md overflow-hidden">
        <!-- Table Header -->
        <div class="hidden md:grid grid-cols-12 bg-gray-50 p-4 border-b border-gray-200 font-semibold text-gray-700">
          <div class="col-span-2">Package ID</div>
          <div class="col-span-4">Destination</div>
          <div class="col-span-4">Courier</div>
          <div class="col-span-2 text-right">Actions</div>
        </div>

        <!-- Rows -->
        <div class="divide-y divide-gray-200">
          <!-- Loading -->
          <div v-if="loading" class="text-center p-8 text-gray-500">
            <i class="fas fa-spinner animate-spin text-2xl mb-2"></i>
            <p>Loading data...</p>
          </div>
          <!-- Empty -->
          <div v-else-if="packages.length === 0" class="p-8 text-center text-gray-500">
            No packages to assign.
          </div>
          <!-- Data Rows -->
          <div v-else>
            <div
              v-for="pkg in packages"
              :key="pkg.id"
              class="hidden md:grid grid-cols-12 items-center p-4 hover:bg-gray-50"
            >
              <div class="col-span-2 text-gray-800 font-medium">{{ pkg.id }}</div>
              <div class="col-span-4 text-gray-600 truncate" :title="pkg.deliveryAddress">
                {{ pkg.deliveryAddress }}
              </div>
              <div class="col-span-4 text-gray-700 flex items-center space-x-2">
                <template v-if="!pkg.courier">
                  <select
                    v-model="pkg.selectedCourier"
                    class="border border-gray-300 rounded-lg px-3 py-2 focus:outline-none focus:ring-2 focus:ring-purple-500"
                  >
                    <option value="" disabled>Select courier</option>
                    <option
                      v-for="c in couriers"
                      :key="c.id"
                      :value="c.id"
                    >
                      {{ c.name }}
                    </option>
                  </select>
                </template>
                <template v-else>
                  <span>{{ pkg.courier.name }}</span>
                </template>
              </div>
              <div class="col-span-2 text-right">
                <button
                  v-if="pkg.selectedCourier"
                  @click="assignCourier(pkg)"
                  class="bg-purple-600 text-white px-4 py-2 rounded-lg hover:bg-purple-700 transition"
                >
                  Assign
                </button>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'AssignCourierView',
  data() {
    return {
      packages: [],
      couriers: [],
      loading: false,
    }
  },
  methods: {
    async refreshData() {
      this.loading = true
      await Promise.all([this.fetchPackages(), this.fetchCouriers()])
      this.loading = false
    },
    async fetchPackages() {
      try {
        const { data } = await axios.get('/package/NoCourier')
        // initialize selectedCourier
        this.packages = data.map(p => ({ ...p, selectedCourier: null }))
      } catch (e) {
        console.error('Error fetching packages:', e)
      }
    },
    async fetchCouriers() {
      try {
        const { data } = await axios.get('/courier')
        this.couriers = data
      } catch (e) {
        console.error('Error fetching couriers:', e)
      }
    },
    async assignCourier(pkg) {
      if (!pkg.selectedCourier) return
      try {
        await axios.put(`/package/AssignCourier/${pkg.id}/${pkg.selectedCourier}`)
        // refresh to reflect changes
        this.refreshData()
      } catch (e) {
        console.error('Error assigning courier:', e)
      }
    },
  },
  mounted() {
    this.refreshData()
  }
}
</script>

<style scoped>
.gradient-bg {
  background: linear-gradient(135deg, #6b21a8 0%, #9333ea 100%);
}
</style>
