import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      redirect: { name: "home" }
    },
    {
      path: "/home",
      name: "home",
      component: () => import('../views/pages/Home.vue')
    },
    {
      path: "/reservation",
      name: "reservation",
      component: () => import('../views/pages/Reservation.vue')
    },
    {
      path: "/dashboard",
      name: "dashboard",
      component: () => import('../views/pages/Dashboard.vue')
    },
    {
      path: "/dashboard/tables",
      name: "tables",
      component: () => import('../views/tables/DashboardTables.vue')
    },
  ]
})

export default router