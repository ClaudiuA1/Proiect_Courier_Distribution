<template>
  <HomePage />
 <createPackage />
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <h1>Package Tracking System</h1>
        <p>Welcome to the Package Tracking System. You can create and track packages here.</p>
      </div>
    </div>
  </div>
</template>

<script>
import HomePage from './components/HomePage.vue'
import createPackage from './components/AddPackage.vue'

export default {
  name: 'PackageTrackingApp',
  components: { HomePage, createPackage },
}
</script>
