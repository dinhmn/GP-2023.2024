<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <div class="flex flex-col justify-between">
    <div class="grid grid-cols-4 gap-5 text-white h-[100px]">
      <div class="col-span-1 p-4 bg-purple-500 rounded-lg">
        <p class="flex items-center justify-between">
          <strong class="text-lg">Đơn hàng</strong>
          <span class="p-1 px-2 text-black bg-white rounded-full">
            <vue-feather class="w-5 h-5 mt-1" type="shopping-bag"></vue-feather>
          </span>
        </p>
        <h5 class="text-xl">{{ data.length }}</h5>
      </div>
      <div class="col-span-1 p-4 bg-blue-500 rounded-lg">
        <p class="flex items-center justify-between">
          <strong class="text-lg">Số lượng bán</strong>
          <span class="p-1 px-2 text-black bg-white rounded-full">
            <vue-feather class="w-5 h-5 mt-1" type="gift"></vue-feather>
          </span>
        </p>
        <h5 class="text-xl">{{ totalQuantity }}</h5>
      </div>
      <div class="col-span-1 p-4 bg-green-500 rounded-lg">
        <p class="flex items-center justify-between">
          <strong class="text-lg">Đơn hoàn thành</strong>
          <span class="p-1 px-2 text-black bg-white rounded-full">
            <vue-feather class="w-5 h-5 mt-1" type="pocket"></vue-feather>
          </span>
        </p>
        <h5 class="text-xl">{{ totalComplete }}</h5>
      </div>
      <div class="col-span-1 p-4 bg-red-500 rounded-lg">
        <p class="flex items-center justify-between">
          <strong class="text-lg">Tổng tiền thu được</strong>
          <span class="p-1 px-2 text-black bg-white rounded-full">
            <vue-feather class="w-5 h-5 mt-1" type="award"></vue-feather>
          </span>
        </p>
        <h5 class="text-xl">{{ formatPrice(totalPrice) + 'đ' }}</h5>
      </div>
    </div>
    <div class="grid grid-cols-3 gap-2 mt-5">
      <div class="col-span-2 bg-[#0c32477e] px-2 py-4 rounded-lg">
        <Bar
          id="my-chart-id"
          height="600"
          class="w-full"
          v-if="loaded"
          :options="chartOptions"
          :data="chartData"
        />
      </div>
      <div class="col-span-1 bg-[#0c32477e] px-2 py-4 my-calendar rounded-lg">
        <div>
          <Pie v-if="loaded" :options="pieOptions" :data="pieData" />
        </div>
        <div class="mt-8">
          <VDatePicker :attributes="attributes" is-dark="system" expanded theme="dark" />
        </div>
      </div>
    </div>
    <div class="w-full mt-5">
      <table class="w-full text-white">
        <thead class="w-full bg-[#0c3247] text-[#17b1ea]">
          <tr class="rounded-tl-md">
            <th>STT.</th>
            <th>Họ và tên</th>
            <th>Số lượng mua</th>
            <th>Tổng tiền</th>
            <th>Ngày đặt hàng</th>
            <th>Trạng thái</th>
            <th class="w-[150px]">Hoạt động</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in dataDetail" :key="item.orderId">
            <td>{{ index + 1 }}</td>
            <td>
              {{
                (item.lastName !== null ? item.lastName : '') +
                ' ' +
                (item.firstName !== null ? item.firstName : '')
              }}
            </td>
            <td>{{ item.totalQuantity }}</td>
            <td>{{ formatPrice(item.totalOrderPrice) + ' đ' }}</td>
            <td>{{ new Date(item.createdDate).toLocaleString().replaceAll('/', '-') }}</td>
            <td>
              <strong
                v-if="Number(item.orderStatus) === 0"
                class="px-[20px] py-[4px] text-xs text-yellow-700 bg-yellow-400 rounded-full"
                >Chờ duyệt</strong
              >
              <strong
                v-if="Number(item.orderStatus) === 1"
                class="px-[20px] py-[4px] text-xs min-w-[100px] text-green-700 bg-green-400 rounded-full"
                >Hoàn thành</strong
              >
              <strong
                v-if="Number(item.orderStatus) === 2"
                class="px-[20px] py-[4px] text-xs text-red-700 bg-red-400 rounded-full"
                >Hủy</strong
              >
            </td>
            <td class="flex items-center w-[150px] justify-around text-xs cursor-pointer">
              <button class="bg-blue-500">Xem</button>
              <button class="bg-green-500">In</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script lang="">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { Bar, Pie } from 'vue-chartjs'
import axios from 'axios'
import OrderService from '../../stores/modules/OrderService.js'
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
  ArcElement
} from 'chart.js'
ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale, ArcElement)
const date = ref(new Date())
const attributes = ref([
  {
    highlight: true,
    dates: [new Date()]
  }
])
const status = ['done', 'pending', 'wait']

const data = ref({})
const totalQuantity = ref(0)
const totalPrice = ref(0)
const totalComplete = ref(0)
const dataDetail = ref([])
const router = useRouter()
const pieData = ref([])
export default {
  name: 'BarChart',
  components: { Bar, Pie },
  data: () => ({
    loaded: false,
    chartData: null,
    pieData: null
  }),
  async mounted() {
    this.loaded = false
    try {
      await OrderService.get('/get-order-each').then(async (response) => {
        dataDetail.value = response.data
      })
      await axios
        .get('http://localhost:8088/api/categories/init-chart-circle')
        .then(async (response) => (pieData.value = response.data))
      this.chartData = {
        labels: [
          'January',
          'February',
          'March',
          'April',
          'May',
          'June',
          'July',
          'August',
          'September',
          'October',
          'November',
          'December'
        ],
        datasets: [
          {
            label: 'Yearly revenue',
            data: dataDetail.value,
            backgroundColor: '#17b1ea',
            width: '300px',
            height: '400px'
          }
        ]
      }
      this.pieData = {
        labels: pieData.value.map((cate) => cate.categoryName),
        datasets: [
          {
            backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1B16'],
            data: pieData.value.map((cate) => cate.countItem)
          }
        ]
      }
      this.loaded = true
    } catch (error) {
      console.log(error)
    }
    try {
      OrderService.get('/get-order-dashboard').then(async (response) => {
        dataDetail.value = response.data
      })
      console.log(dataDetail)
    } catch (error) {
      router.push({ name: '404' })
    }
  },
  setup() {
    try {
      OrderService.get('/get-order-all').then((response) => {
        data.value = response.data
        for (let index = 0; index < response.data.length; index++) {
          const element = response.data[index]
          totalQuantity.value += Number(element.totalQuantity)
          totalPrice.value += Number(element.totalOrderPrice)
          if (element.orderStatus === '1') {
            totalComplete.value += 1
          }
        }
      })
    } catch (error) {
      router.push({ name: '404' })
    }

    function formatPrice(value) {
      let val = (value / 1).toFixed(0).replace('.', ',')
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')
    }
    return {
      chartOptions: {
        responsive: true
      },
      pieOptions: {
        responsive: true,
        maintainAspectRatio: false
      },
      date,
      attributes,
      status,
      data,
      totalPrice,
      totalQuantity,
      formatPrice,
      totalComplete,
      dataDetail
    }
  }
}
</script>
<style lang="css">
.my-calendar :deep(.vc-weekday-1, .vc-weekday-7) {
  color: #6366f1;
}
ul li {
  padding: 4px 20px;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 3px;
  cursor: pointer;
}
ul li.active {
  background-color: #0c3247;
  color: #17b1ea;
}
table,
tr,
td,
th {
  padding: 5px 10px;
  text-align: center;
}

table tbody tr {
  border-bottom: 2px solid #464b53;
}
table tbody tr td {
  padding: 5px;
}
</style>
