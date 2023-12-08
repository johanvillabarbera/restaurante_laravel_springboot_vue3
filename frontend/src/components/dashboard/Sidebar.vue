<template>

<q-drawer
        v-model="drawer"
        show-if-above
        :width="200"
        :breakpoint="500"
        bordered
        :class="$q.dark.isActive ? 'bg-grey-9' : 'bg-grey-3'"
      >
        <q-scroll-area class="fit">
          <q-list>

            <template v-for="(menuItem, index) in menuList" :key="index">
              <q-item clickable :active="menuItem.label === 'Outbox'" v-ripple>
                <q-item-section avatar>
                  <q-icon :name="menuItem.icon" />
                </q-item-section>
                <q-item-section>
                  <router-link :to="menuItem.routerLink" style="text-decoration: none; color: inherit;">
                    <q-item-label>{{ menuItem.label }}</q-item-label>
                  </router-link>
                </q-item-section>
              </q-item>
              <q-separator :key="'sep' + index"  v-if="menuItem.separator" />
            </template>

          </q-list>
        </q-scroll-area>
      </q-drawer>

</template>
<script setup>


    const menuList = [
  {
    icon: 'inbox',
    label: 'Dashboard',
    separator: true,
    routerLink: '/dashboard'
  },
  {
    icon: 'send',
    label: 'Tables',
    separator: false,
    routerLink: '/dashboard/tables'
  },
  {
    icon: 'delete',
    label: 'Menus',
    separator: false,
    routerLink: '/dashboard/menus'
  },
  {
    icon: 'error',
    label: 'Spam',
    separator: true,
    routerLink: '/home'
  },
  {
    icon: 'settings',
    label: 'Settings',
    separator: false,
    routerLink: '/home'
  },
  {
    icon: 'feedback',
    label: 'Send Feedback',
    separator: false,
    routerLink: '/home'
  },
  {
    icon: 'home',
    iconColor: 'primary',
    label: 'Home',
    separator: false,
    routerLink: '/home'
  }
]
</script>

<style lang="css">

</style>