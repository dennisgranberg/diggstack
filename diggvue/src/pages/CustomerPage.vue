<script setup lang="ts">

import { ref } from 'vue';
import CustomerForm from "@/components/customer/CustomerForm.vue";
import CustomerTable from "@/components/customer/CustomerTable.vue";

const customerTable = ref();

function refreshTable() {
  customerTable.value?.fetchData();
}
</script>

<template>
  <div>
    <CustomerForm @customer-created="refreshTable" />
    <CustomerTable ref="customerTable" />
  </div>
</template>
