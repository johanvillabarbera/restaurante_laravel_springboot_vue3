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
      path: "/reservation/:filters",
      name: "reservationFilters",
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
    {
      path: "/dashboard/tables/create",
      name: "tablesCreate",
      component: () => import('../views/tables/createTables.vue')
    },
    {
      path: "/dashboard/tables/update/:id",
      name: "tablesUpdate",
      component: () => import('../views/tables/updateTables.vue')
    },
    {
      path: "/dashboard/menus",
      name: "menus",
      component: () => import('../views/menus/DashboardMenus.vue')
    },
    {
      path: "/dashboard/menus/create",
      name: "menusCreate",
      component: () => import('../views/menus/createMenus.vue')
    },
    {
      path: "/dashboard/menus/update/:id",
      name: "menusUpdate",
      component: () => import('../views/menus/updateMenus.vue')
    },
    {
      path: "/login",
      name: "login",
      component: () => import('../views/pages/Login.vue')
    },
  ]
})

export default router