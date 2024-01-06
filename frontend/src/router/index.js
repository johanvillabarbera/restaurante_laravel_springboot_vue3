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
      path: "/dashboard/reservations",
      name: "reservations",
      component: () => import('../views/reservations/DashboardReservations.vue'),
      beforeEnter: authGuards.authGuardAdmin, meta: { requiresAuth: true }
    },
    {
      path: "/login",
      name: "login",
      component: () => import('../views/pages/Login.vue'),
      beforeEnter: authGuards.noAuthGuard, meta: { requiresAuth: true }
    },
    // PROFILE
    {
      path: "/profile",
      name: "profile",
      component: () => import('../views/pages/Profile.vue'),
      beforeEnter: authGuards.authGuard, meta: { requiresAuth: true }
    },
    // REGISTER
    {
      path: "/register",
      name: "register",
      component: () => import('../views/pages/Register.vue'),
      beforeEnter: authGuards.noAuthGuard, meta: { requiresAuth: true }
    },
    // VERIFY ACCOUNT
    {
      path: "/confirm_account",
      name: "confirmAccount",
      component: () => import('../views/pages/ConfirmAccount.vue'),
    },
    {
      path: "/confirm_account/:token",
      name: "confirmAccount",
      component: () => import('../views/pages/ConfirmAccount.vue'),
      beforeEnter: authGuards.noAuthGuard, meta: { requiresAuth: true }
    },
    {
      path: "/develop",
      name: "develop",
      component: () => import('../views/pages/Develop.vue'),
    }
  ]
})

export default router