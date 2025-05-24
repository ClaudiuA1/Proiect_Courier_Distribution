<template>
  <div class="bg-gray-100 min-h-screen">
    <div class="container mx-auto px-4 py-8">
      <!-- Header -->
      <header class="gradient-bg rounded-xl shadow-lg mb-8 p-6 text-white">
        <div class="flex flex-col md:flex-row md:items-center justify-between gap-4">
          <div class="flex items-center space-x-4">
            <div class="w-12 h-12 bg-white rounded-lg flex items-center justify-center">
              <i class="fas fa-box-open text-purple-700 text-2xl"></i>
            </div>
            <h1 class="text-2xl md:text-3xl font-bold">Package Tracking System</h1>
          </div>
          <button
            @click="refreshPackages"
            :disabled="loading"
            class="bg-white text-purple-700 hover:bg-purple-50 px-6 py-2 rounded-lg font-medium flex items-center space-x-2 transition-colors duration-200"
          >
            <i :class="loading ? 'fas fa-spinner animate-spin' : 'fas fa-sync-alt'"></i>
            <span>{{ loading ? 'Refreshing...' : 'Refresh Packages' }}</span>
          </button>
        </div>
      </header>

      <!-- Main Table -->
      <main class="bg-white rounded-xl shadow-md overflow-hidden">
        <!-- Table Header (desktop) -->
        <div class="hidden md:grid grid-cols-12 bg-gray-50 p-4 border-b border-gray-200 font-semibold text-gray-700">
          <div class="col-span-2">Tracking ID</div>
          <div class="col-span-3">Destination</div>
          <div class="col-span-2">Last Update</div>
          <div class="col-span-2">Status</div>
          <div class="col-span-1">Pay on delivery</div>
          <div class="col-span-2 text-right">Actions</div>
        </div>

        <!-- Package List -->
        <div class="divide-y divide-gray-200">
          <!-- Loading -->
          <div v-if="loading" class="text-center p-8 text-gray-500">
            <i class="fas fa-spinner animate-spin text-2xl mb-2"></i>
            <p>Loading packages...</p>
          </div>

          <!-- Empty State -->
          <div
            v-else-if="paginated.length === 0"
            class="bg-white rounded-xl shadow-md p-8 text-center"
          >
            <i class="fas fa-box-open text-gray-300 text-5xl mb-4"></i>
            <h3 class="text-xl font-medium text-gray-700 mb-2">No Packages Found</h3>
            <p class="text-gray-500 mb-4">There are currently no packages to display.</p>
            <button
              @click="refreshPackages"
              class="bg-purple-600 hover:bg-purple-700 text-white px-6 py-2 rounded-lg font-medium transition-colors duration-200"
            >
              Try Again
            </button>
          </div>

          <!-- Packages -->
          <div v-else>
            <div
              v-for="pkg in paginated"
              :key="pkg.id"
              class="hidden md:grid grid-cols-12 items-center p-4 package-card transition-all duration-200 hover:bg-gray-50"
            >
              <!-- Tracking ID -->
              <div class="col-span-2 font-medium text-gray-800">
                {{ pkg.id }}
              </div>

              <!-- Destination -->
              <div
                class="col-span-3 text-gray-600 truncate"
                :title="pkg.deliveryAddress"
              >
                {{ pkg.deliveryAddress }}
              </div>

              <!-- Last Update -->
              <div class="col-span-2 text-gray-500">
                {{ formatDate(pkg.createdOn) }}
              </div>

              <!-- Status -->
              <div class="col-span-2">
                <span
                  :class="statusClass(pkg.status) + ' text-white px-3 py-1 rounded-full text-sm'"
                >
                  {{ pkg.status }}
                </span>
              </div>

              <!-- Pay on Delivery -->
              <div class="col-span-1 text-gray-800 font-medium">
                {{ pkg.payOnDelivery ? 'Yes' : 'No' }}
              </div>

              <!-- Actions -->
              <div class="col-span-2 text-right">
                <button
                  @click="openModal(pkg)"
                  class="text-purple-600 hover:text-purple-800"
                >
                  <i class="fas fa-eye"></i>
                </button>
              </div>
            </div>
          </div>
        </div>

        <!-- Pagination -->
        <div
          v-if="!loading && total > 0"
          class="flex items-center justify-between p-4 border-t border-gray-200"
        >
          <div class="text-sm text-gray-600">
            Showing {{ start }} to {{ end }} of {{ total }} packages
          </div>
          <div class="flex space-x-2">
            <button
              @click="prevPage"
              :disabled="page === 1"
              class="px-3 py-1 rounded border border-gray-300 bg-white text-gray-700 disabled:opacity-50"
            >
              <i class="fas fa-chevron-left"></i>
            </button>
            <button
              @click="nextPage"
              :disabled="page * perPage >= total"
              class="px-3 py-1 rounded border border-gray-300 bg-white text-gray-700 disabled:opacity-50"
            >
              <i class="fas fa-chevron-right"></i>
            </button>
          </div>
        </div>
      </main>
    </div>

    <!-- Package Details Modal -->
    <div
      v-if="showModal"
      class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50"
    >
      <div class="bg-white rounded-xl shadow-xl max-w-2xl w-full max-h-[90vh] overflow-y-auto">
        <!-- Header -->
        <div class="flex justify-between items-center p-6 border-b border-gray-200">
          <h3 class="text-xl font-bold text-gray-800">Package Details</h3>
          <button @click="showModal = false" class="text-gray-400 hover:text-gray-600">
            <i class="fas fa-times text-2xl"></i>
          </button>
        </div>
        <!-- Body -->
        <div class="p-6">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6 mb-6">
            <!-- Tracking Info -->
            <div>
              <h4 class="font-medium text-gray-700 mb-2">Tracking Information</h4>
              <div class="space-y-4">
                <div>
                  <p class="text-sm text-gray-500">Tracking ID</p>
                  <p class="font-medium">{{ modalPkg.id }}</p>
                </div>
                <div>
                  <p class="text-sm text-gray-500">Status</p>
                  <p
                    :class="statusClass(modalPkg.status) + ' inline-block px-3 py-1 rounded-full text-sm font-medium text-white'"
                  >
                    {{ modalPkg.status }}
                  </p>
                </div>
                <div>
                  <p class="text-sm text-gray-500">Estimated Delivery</p>
                  <p class="font-medium">
                    {{ formatDate(modalPkg.deliveryDate, { month:'long', day:'numeric', year:'numeric' }) }}
                  </p>
                </div>
              </div>
            </div>
            <!-- Recipient Info -->
            <div>
              <h4 class="font-medium text-gray-700 mb-2">Recipient Details</h4>
              <div class="space-y-4">
                <div>
                  <p class="text-sm text-gray-500">Destination</p>
                  <p class="font-medium">{{ modalPkg.destination }}</p>
                </div>
                <div>
                  <p class="text-sm text-gray-500">Contact</p>
                  <p class="font-medium">{{ modalPkg.contact }}</p>
                </div>
              </div>
            </div>
          </div>
          <!-- Shipping History -->
          <h4 class="font-medium text-gray-700 mb-4">Shipping History</h4>
          <div class="space-y-4">
            <div
              v-for="(e, i) in modalPkg.shippingHistory"
              :key="i"
              class="flex items-start"
            >
              <div class="flex flex-col items-center mr-4">
                <div :class="statusClass(e.status) + ' w-3 h-3 rounded-full mt-1'"></div>
                <div class="w-px h-full bg-gray-300"></div>
              </div>
              <div class="pb-4">
                <div class="flex justify-between">
                  <p class="font-medium text-gray-800">{{ e.status }}</p>
                  <p class="text-sm text-gray-500">{{ formatDate(e.date) }}</p>
                </div>
                <p class="text-sm text-gray-600">{{ e.location }}</p>
                <p class="text-sm text-gray-500 mt-1">{{ e.description }}</p>
              </div>
            </div>
          </div>
        </div>
        <!-- Footer -->
        <div class="p-6 border-t border-gray-200 flex justify-end space-x-3">
          <button
            @click="printLabel"
            class="px-4 py-2 border border-gray-300 rounded-lg text-gray-700 hover:bg-gray-50"
          >
            <i class="fas fa-print mr-2"></i> Print Label
          </button>
          <button
            @click="trackOnMap"
            class="px-4 py-2 bg-purple-600 text-white rounded-lg hover:bg-purple-700"
          >
            <i class="fas fa-map-marker-alt mr-2"></i> Track on Map
          </button>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import axios from 'axios';

export default {
  name: 'PackageTracker',
  data() {
    return {
      packages: [],
      loading: false,
      page: 1,
      perPage: 5,
      showModal: false,
      modalPkg: {}
    }
  },
  computed: {
    total() { return this.packages.length },
    start() { return (this.page-1)*this.perPage + 1 },
    end() { return Math.min(this.page*this.perPage, this.total) },
    paginated() {
      const start = (this.page-1)*this.perPage;
      return this.packages.slice(start, start+this.perPage);
    }
  },
  methods: {
    async refreshPackages() {
      this.loading = true;
      await this.fetchPackages();
      this.loading = false;
    },
    prevPage() {
      if (this.page > 1) this.page--;
    },
    nextPage() {
      if (this.page * this.perPage < this.total) this.page++;
    },
    openModal(pkg) {
      this.modalPkg = pkg;
      this.showModal = true;
    },
    printLabel() {
      alert('Printing label...');
    },
    trackOnMap() {
      alert('Opening map view...');
    },
    statusClass(status) {
      switch (status.toLowerCase()) {
        case 'delivered':   return 'status-delivered';
        case 'new':         return 'status-new';
        case 'pending':     return 'status-pending';
        
        default:            return 'bg-gray-500';
      }
    },
    formatDate(date, opts = null) {
      return new Date(date).toLocaleDateString('en-US', opts || {
        year:'numeric',month:'short',day:'numeric',hour:'2-digit',minute:'2-digit'
      });
    },
    async fetchPackages() {
      try {
        const { data } = await axios.get('http://localhost:8080/package');
        this.packages = data;
      } catch (error) {
        console.error('Error fetching packages:', error);
      }
    }
  },
  mounted() {
    this.refreshPackages();
  }
}
</script>

<style>
.gradient-bg {
  background: linear-gradient(135deg, #6b21a8 0%, #9333ea 100%);
}
.package-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 10px 15px -3px rgba(0,0,0,0.1),0 4px 6px -2px rgba(0,0,0,0.05);
}
.status-delivered   { background-color: #10b981; }
.status-in-transit  { background-color: #f59e0b; }
.status-pending     { background-color: #64748b; }
.status-failed      { background-color: #ef4444; }
</style>
