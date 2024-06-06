<script setup>
import{ArrowLeftBold
  
}from  '@element-plus/icons-vue';

  import{useFlowerStore} from'@/stores/index.js'
  const FlowerStore=useFlowerStore()
  const id=FlowerStore.selectedFlowerId


  import { useRouter } from 'vue-router';
  import { ref } from 'vue';
  const router=useRouter()
  const drawerVisiable=ref(false)

  const pics=[
    {
      id:1,
      url:'https://big-event-ct.oss-cn-beijing.aliyuncs.com/1d907c41-3908-434a-bd02-8cb8052dfe96.png'
    },
    {
      id:2,
      url:'https://big-event-ct.oss-cn-beijing.aliyuncs.com/6426fd94-8ad2-4427-91be-eaa17a688cae.png'
    }
  ]
  
  const flowerDetail={
    id:1,
    name:'rose',
    price:34.3,
    introduction:'很长很长很长哼唱很长很长很长哼唱很长27字',
    num:24
  }

  const num = ref(1)

  const flowerComments=ref([
    {
      comid:1,
      name:'yawn',
      score:3,
      comments:'hoahoa',
      time:'2023-2-2'
    },
    {
      comid:2,
      name:'wenxin',
      score:4,
      comments:'下次还来',
      time:'2024-5-2'
    },
  ])
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header class="header">
        <el-link @click="router.back();
        FlowerStore.setSelectedFlowerId('')">
          <el-icon>
            <ArrowLeftBold />
          </el-icon>返回</el-link>
        <el-link type="warning" @click="drawerVisiable=true">查看评论</el-link>

      </el-header>
      <el-main class="main">
        <el-row>
          <el-col :span="14">
            <el-carousel arrow="always">
              <el-carousel-item class="carousel" v-for="item in pics" :key="item.id">
                <img :src=item.url class="image" />
              </el-carousel-item>
            </el-carousel>
          </el-col>
          <el-col :span="10">
            <el-card shadow="always" style="height: 55vh ;">
              <template #header>
                <div class="card-header">
                  <span style="color:#95d475 ;">{{flowerDetail.name}}</span>
                  <span style="color: red;">￥{{flowerDetail.price}}</span>
                </div>

              </template>
              <table>
                <tr style="height: 40px">
                  <td width="60px">
                    描述:
                  </td>
                  <td width="150px" style="height: 50px;">
                    {{ flowerDetail.introduction }}
                  </td>
                </tr>
                <tr style="height: 40px">
                  <td width="60px">
                    库存:
                  </td>
                  <td width="150px">
                    {{ flowerDetail.num }}
                  </td>
                </tr>
              </table>
              <div class="card-buttons">
                <el-input-number v-model="num" :min="1" :max="flowerDetail.num" @change="handleChange" size="small" />
              </div>
              <div style="margin-top: 10px;">
                <el-button type="primary" size="small">加入购物车</el-button>
                <el-button type="success" size="small">立即购买</el-button>
              </div>
            </el-card>
          </el-col>

        </el-row>

      </el-main>

    </el-container>
  </div>

  <el-drawer v-model="drawerVisiable" title="评论" direction="rtl" size="50%">
    <el-table :data="flowerComments" with="100%">
      <el-table-column label="评论人" prop="name"></el-table-column>
      <el-table-column label="内容" prop="comments"></el-table-column>
      <el-table-column label="评分">
        <template #default="{ row }">
          <el-rate v-model="row.score" disabled show-score text-color="#ff9900" 
          score-template="{row.score} points" />
        </template>

      </el-table-column>
      <el-table-column label="评论时间" prop="time"></el-table-column>
    </el-table>
  </el-drawer>
</template>



<style>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.carousel{
  height: 55vh;
}

.common-layout{
  width: 80%;
  margin-left: 10%;
  
}
.header{
  display: flex;
  background-color:#529b2e;
  height: 30px;
  /* border-radius:  20px 20px  ; */
  justify-content: space-between;
}

</style>