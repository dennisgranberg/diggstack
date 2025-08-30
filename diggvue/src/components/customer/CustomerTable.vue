<script setup lang="ts">
import {ref, computed, onMounted} from 'vue';
import type {Customer} from '@/types/Customer';
import {getCustomers} from '@/services/customerService';

const customers = ref<Customer[]>([]);
const currentPage = ref(1);
const pageSize = 10;

const paginatedCustomers = computed(() => {
  const start = (currentPage.value - 1) * pageSize;
  return customers.value.slice(start, start + pageSize);
});

const totalPages = computed(() =>
    Math.ceil(customers.value.length / pageSize)
);

function nextPage() {
  if (currentPage.value < totalPages.value) currentPage.value++;
}

function prevPage() {
  if (currentPage.value > 1) currentPage.value--;
}

async function fetchData() {
  customers.value = await getCustomers();
}

onMounted(fetchData);
</script>

<template>
  <div>
    <h2>Customer List</h2>
    <table>
      <thead>
      <tr>
        <th>Name</th>
        <th>Address</th>
        <th>Email</th>
        <th>Phone</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="customer in paginatedCustomers" :key="customer.email">
        <td>{{ customer.name }}</td>
        <td>{{ customer.address }}</td>
        <td>{{ customer.email }}</td>
        <td>{{ customer.telephone }}</td>
      </tr>
      </tbody>
    </table>

    <div>
      <button @click="prevPage" :disabled="currentPage === 1">Previous</button>
      <span>Page {{ currentPage }}</span>
      <button @click="nextPage" :disabled="currentPage === totalPages">Next</button>
    </div>
  </div>
</template>

<style scoped>
table {
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ccc;
  padding: 8px;
}
</style>
