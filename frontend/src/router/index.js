import { createRouter, createWebHistory } from 'vue-router';
import authGuards from '../services/guards/authGuards';
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
      component: () => import('../views/pages/Dashboard.vue'),
      beforeEnter: authGuards.authGuardAdmin, meta: { requiresAuth: true }
    },
    {
      path: "/dashboard/tables",
      name: "tables",
      component: () => import('../views/tables/DashboardTables.vue'),
      beforeEnter: authGuards.authGuardAdmin, meta: { requiresAuth: true }
    },
    {
      path: "/dashboard/tables/create",
      name: "tablesCreate",
      component: () => import('../views/tables/createTables.vue'),
      beforeEnter: authGuards.authGuardAdmin, meta: { requiresAuth: true }
    },
    {
      path: "/dashboard/tables/update/:id",
      name: "tablesUpdate",
      component: () => import('../views/tables/updateTables.vue'),
      beforeEnter: authGuards.authGuardAdmin, meta: { requiresAuth: true }
    },
    {
      path: "/dashboard/menus",
      name: "menus",
      component: () => import('../views/menus/DashboardMenus.vue'),
      beforeEnter: authGuards.authGuardAdmin, meta: { requiresAuth: true }
    },
    {
      path: "/dashboard/menus/create",
      name: "menusCreate",
      component: () => import('../views/menus/createMenus.vue'),
      beforeEnter: authGuards.authGuardAdmin, meta: { requiresAuth: true }
    },
    {
      path: "/dashboard/menus/update/:id",
      name: "menusUpdate",
      component: () => import('../views/menus/updateMenus.vue'),
      beforeEnter: authGuards.authGuardAdmin, meta: { requiresAuth: true }
    },
    {
      path: "/login",
      name: "login",
      component: () => import('../views/pages/Login.vue'),
      beforeEnter: authGuards.noAuthGuard, meta: { requiresAuth: true }
    },
  ]
})

export default router