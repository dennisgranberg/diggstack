<script setup lang="ts">
import { reactive } from 'vue';
import { createCustomer } from '@/services/customerService';

const emit = defineEmits(['customer-created']);

const form = reactive({
  name: '',
  address: '',
  email: '',
  telephone: '',
});

async function handleSubmit() {
  await createCustomer({ ...form });
  emit('customer-created'); // Let the parent know to refresh
  Object.assign(form, { name: '', address: '', email: '', telephone: '' });
}
</script>

<template>
  <div>
    <h2>Create Customer</h2>
    <form @submit.prevent="handleSubmit">
      <input v-model="form.name" placeholder="Name" required />
      <input v-model="form.address" placeholder="Address" required />
      <input v-model="form.email" type="email" placeholder="Email" required />
      <input v-model="form.telephone" placeholder="Phone" required />
      <button type="submit">Add Customer</button>
    </form>
  </div>
</template>

<style scoped>
input {
  display: block;
}
</style>
