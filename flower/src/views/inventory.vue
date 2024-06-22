<script setup>
import { ref } from 'vue';
import {
  ArrowRight,
  CaretBottom,
  CaretTop,
  Warning,
} from '@element-plus/icons-vue'

import{Edit,Delete,ShoppingCartFull} from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox} from 'element-plus';

// ---------------------
// 接口引入
import{inventoryListService,
    inventoryAddService,
    inventoryAllListService,
    lossAddService,


}from '@/api/inventory.js'

import{flowerListService}from '@/api/flower.js'

// ---------------------
// 分页
const total=ref(20)
const pageNum=ref(1)
const pageSize=ref(5)
// ---------------------
// 四个card
const purchaseAll=ref(1)
const orderAll=ref(2)
const reorderAll=ref(43)
const profitAll=ref(44)
const  inventoryAllList=ref()
const  getinventoryAllList=async()=>{
   const result=await inventoryAllListService()
   inventoryAllList.value=result.data
   let ordprice=0
    let profit=0
    let reoprice=0
    let purprice=0
    for(let i=0;i< inventoryAllList.value.length;i++){
        let now=inventoryAllList.value[i]

        ordprice+=now.ordprice
        profit+=now.profit
        reoprice+=now.reoprice
        purprice+=now.purprice

    }
    purchaseAll.value=purprice
    orderAll.value=ordprice
    reorderAll.value=reoprice
    profitAll.value=profit
}
getinventoryAllList()
// ---------------------
// 搜索
const toselect=ref('')
const select=async()=>{
    const result =await inventoryListService(toselect.value,pageNum.value)
    inventoryList.value=result.data.items
    total.value=result.data.total 

    for(let i=0;i< inventoryList.value.length;i++){
        let now=inventoryList.value[i]



        now.ordprice=now.ordprice.toFixed(2)
        now.profit=now.profit.toFixed(2)
        now.reoprice=now.reoprice.toFixed(2)
        now.purprice=now.purprice.toFixed(2)
        now.itime=now.itime.substring(0, 10)
    }

}
select()

// ---------------------
// baseInfo
const inventoryList=ref([
    {
        iid:2,
        purnum:2,
        purprice:3,
        ordnum:3,
        ordprice:3,
        reoprice:3,
        profit:3,
        itime:234234,
        realnum:4
    },
    
])


const lossViewList=ref([
    {
        fid:1,
        fname:'f1',
        lsum:29,
        lcount:2,
    },
    

])

const orderViewList=ref([
    {
        fid:1,
        fname:'f1',
        totalprice:123,
        totalnum:2,
    },
   
   
])

const purchaseViewList=ref([
    {
        fid:12,
        fname:'f1',
        totalprice:123,
        totalnum:2,
    },  
])

const reordesViewList=ref([
    {
        fid:1,
        fname:'f1',
        totalprice:123,
        recount:2,
    }, 
    
])

const scoreViewList=ref([
    {
        fid:1,
        fname:'f1',
        totalscore:123,
        records:2,
        avgscore:1
    },
   
])

// ---------------------
// 新增盘点
const realnum=ref(1)
const inventoryDialogVisiable=ref(false)

inventoryAddService
const inventoryAdd=async()=>{

    ElMessageBox.confirm(
            `你确认在库花卉数为  ${realnum.value} 支吗`,
            '温馨提示',
            {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning',
            }
        )
            .then(async () => {
                const result=await inventoryAddService(realnum.value)
                // console.log( realnum.value)
                ElMessage.success("盘点成功")
                getinventoryAllList()
                realnum.value=1
            })
            .catch(() => {
                //用户点击了取消
                ElMessage({
                    type: 'info',
                    message: '取消退出',
                })
            })
   

}



// ---------------------
// 统计损失
const seletedFlowerId=ref('')
const lossNum=ref(1)
const lossDialogVisiable=ref(false)
const flowerList = ref([
  {
    fid: 1,
    fname: 'f1',
  
  },
  {
    fid: 2,
    fname: 'f2',
  
  },

])

const getflowerList=async()=>{
    const result =await flowerListService()
    flowerList.value=result.data.items
    console.log(result.data)
}
getflowerList()

const addLoss=async()=>{
    const result =await lossAddService(seletedFlowerId.value,lossNum.value)
    ElMessage.success("统计成功")
}

// ---------------------
// 5个抽屉
const visibleDrawer1=ref(false)
const visibleDrawer4=ref(false)
const visibleDrawer5=ref(false)
const visibleDrawer23=ref(false)
const seletedInventory=ref('')

const showInventory=()=>{
    if(seletedInventory.value==1){
        visibleDrawer1.value=true
    }else if(seletedInventory.value==4){
        visibleDrawer4.value=true
    }else if(seletedInventory.value==5){
        visibleDrawer5.value=true
    }else if(seletedInventory.value==2||seletedInventory.value==3){
        visibleDrawer23.value=true
    }
    else {
        ElMessage.info('请选择要查看的项目')
       
    }
}


</script>

<template>

    <!-- 4个card -->
    <el-row :gutter="10">
        <el-col :span="6">
            <div class="statistic-card">
                <el-statistic :value="purchaseAll" title="New transactions today" 
                prefix="￥" value-style="color: #79bbff" precision="2">
                    <template #title>
                        <div style="display: inline-flex; align-items: center ;font-size: x-large;">
                           新货支出
                        </div>
                    </template>
                </el-statistic>
            </div>
        </el-col>
        <el-col :span="6">
            <div class="statistic-card">
                <el-statistic :value="orderAll" title="New transactions today" 
                prefix="￥" value-style="color: #eebe77" precision="2">
                    <template #title>
                        <div style="display: inline-flex; align-items: center ;font-size: x-large;">
                            总销售额
                        </div>
                    </template>
                </el-statistic>
            </div>
        </el-col>
        <el-col :span="6">
            <div class="statistic-card">
                <el-statistic :value="reorderAll" title="New transactions today" 
                prefix="￥" value-style="color: #f89898" precision="2">
                    <template #title>
                        <div style="display: inline-flex; align-items: center ;font-size: x-large;">
                            售后支出
                        </div>
                    </template>
                </el-statistic>
            </div>
        </el-col>
        <el-col :span="6">
            <div class="statistic-card">
                <el-statistic :value="profitAll" title="New transactions today" 
                prefix="￥" value-style="color: #95d475" precision="2">
                    <template #title>
                        <div style="display: inline-flex; align-items: center ;font-size: x-large;">
                            总利润
                        </div>
                    </template>
                </el-statistic>
            </div>
        </el-col>
    </el-row>

    <!-- 数据展示 -->
    <el-card class="page-container ">

        <!-- 顶部 -->
        <template #header>
            <div class="header">
                <div style="display: flex;">
                    <el-input v-model="toselect" placeholder="搜时间"></el-input>

                    <el-button type="primary" @click="select(); ElMessage.success('搜索成功');">搜索</el-button>
                    <el-button type="default"
                        @click="toselect='';select();ElMessage.success('重置成功')">重置</el-button>
                </div>
                <div>
                    <el-select v-model="seletedInventory" style="width: 120px;" placeholder="按花卉统计" >
                        <el-option :key=1 label=" 损失盘点 " :value=1></el-option>
                        <el-option :key=2 label=" 订单盘点 " :value=2></el-option>
                        <el-option :key=3 label=" 进货盘点 " :value=3></el-option>
                        <el-option :key=4 label=" 售后盘点 " :value=4></el-option>
                        <el-option :key=5 label=" 评论盘点 " :value=5></el-option>
                    </el-select>
                    <el-button type="primary" @click="showInventory()">查看</el-button>
                 
                </div>
                <div style="display: flex;">                   
                    <el-button type="success" @click="inventoryDialogVisiable=true">新增盘点</el-button>
                    <el-button type="primary" @click="lossDialogVisiable=true">统计损失</el-button>
                </div>
            </div>
        </template>

        <!-- 展示表格 -->
        <el-table class="dataTable" :data="inventoryList" style="width: 100% ">
            <el-table-column label="编号" width="70" prop="iid"></el-table-column>
            <el-table-column label="进货数(支)" width="110" prop="purnum"></el-table-column>
            <el-table-column label="进货支出(元)" width="110" prop="purprice"></el-table-column>
            <el-table-column label="销售数(支)" width="110" prop="ordnum"> </el-table-column>
            <el-table-column label="销售额(元)" width="110" prop="ordprice" > </el-table-column>
            <el-table-column label="售后支出(元)" prop="reoprice" width="110"></el-table-column>
            <el-table-column label="阶段利润(元)" prop="profit"></el-table-column>
            <el-table-column label="在库花卉(支)" prop="realnum"></el-table-column>
            <el-table-column label="盘点时间" prop="itime"></el-table-column>
            
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

        <!-- 分页条 -->
        <el-pagination small background layout="jumper, total,  prev, pager, next" :total="total"
            @current-change="select() " :page-size=pageSize v-model:current-page="pageNum"
            style="margin-top: 20px; justify-content: center" />
    </el-card>





<!-- 新增盘点 -->
  <el-dialog title="新增盘点" v-model="inventoryDialogVisiable" width="30%">
    <el-row>
      <el-col :span="6" :offset="3"><span><label>在库数:</label></span></el-col>
      <el-col :span="13"><el-input-number v-model="realnum" min="1" max="9999"></el-input-number></el-col>
    </el-row>
    <template #footer>
      <span class="dialog">
       
        <el-button type="warning" @click="inventoryDialogVisiable = false;realnum=1;">取消</el-button>
        <el-button type="primary" @click="inventoryAdd(); inventoryDialogVisiable = false">确定</el-button>
      </span>
    </template>
  </el-dialog>



  <!-- 统计损失 -->
  <el-dialog title="统计损失" v-model="lossDialogVisiable" width="30%">
    <el-row>
      <el-col :span="6" :offset="3"><span><label>损失花卉:</label></span></el-col>
      <el-col :span="13"><el-select placeholder="选择花卉" v-model="seletedFlowerId" >
            <el-option v-for="c in flowerList" :key="c.fid" :label="c.fname" :value="c.fid">
            </el-option>
          </el-select>
        </el-col>
    </el-row>
    <el-row>
      <el-col :span="6" :offset="3"><span ><label>损失数:</label></span></el-col>
      <el-col :span="13"><el-input-number v-model="lossNum" min="1" max="9999"></el-input-number></el-col>
    </el-row>
    <template #footer>
      <span class="dialog">
       
        <el-button type="warning" @click="lossDialogVisiable = false;lossNum=1; seletedFlowerId=''">取消</el-button>
        <el-button type="primary" @click="addLoss() ;lossDialogVisiable = false">确定</el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 1抽屉 -->
  <el-drawer v-model="visibleDrawer1" :title="'损失盘点'" direction="rtl" size="50%">
    <el-table class="dataTable" :data="lossViewList" style="width: 100% ">
            <el-table-column label="编号"  prop="fid"></el-table-column>
            <el-table-column label="花卉名"  prop="fname"></el-table-column>
            <el-table-column label="总数(支)"  prop="lcount"></el-table-column>
            <el-table-column label="金额(元)"  prop="lsum"> </el-table-column>
           
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

  </el-drawer>


  <!-- 23抽屉 -->
  <el-drawer v-model="visibleDrawer23" :title="seletedInventory==2?'订单盘点':'进货盘点'" direction="rtl" size="50%">
    <el-table class="dataTable" :data="seletedInventory==2?orderViewList:purchaseViewList" style="width: 100% ">
            <el-table-column label="编号"  prop="fid"></el-table-column>
            <el-table-column label="花卉名"  prop="fname"></el-table-column>
            <el-table-column label="总数(支)"  prop="totalnum"></el-table-column>
            <el-table-column label="金额(元)"  prop="totalprice"> </el-table-column>
           
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

  </el-drawer>

    <!-- 4抽屉 -->
    <el-drawer v-model="visibleDrawer4" :title="'售后盘点'" direction="rtl" size="50%">
    <el-table class="dataTable" :data="reordesViewList" style="width: 100% ">
            <el-table-column label="编号"  prop="fid"></el-table-column>
            <el-table-column label="花卉名"  prop="fname"></el-table-column>
            <el-table-column label="总数(支)"  prop="recount"></el-table-column>
            <el-table-column label="金额(元)"  prop="totalprice"> </el-table-column>
           
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

  </el-drawer>


 <!-- 5抽屉 -->
 <el-drawer v-model="visibleDrawer5" :title="'评论盘点'" direction="rtl" size="50%">
    <el-table class="dataTable" :data="scoreViewList" style="width: 100% ">
            <el-table-column label="编号"  prop="fid"></el-table-column>
            <el-table-column label="花卉名"  prop="fname"></el-table-column>
            <el-table-column label="平均评分"  prop="avgscore"></el-table-column>
            <el-table-column label="评论数"  prop="records"> </el-table-column>
           
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

  </el-drawer>
</template>

<style scoped lang="scss">
.dialog {
  display: flex;
  
  justify-content: center;
  
}
.page-container {
  min-height: 100%;
  box-sizing: border-box;
  justify-content: space-between;

  .header {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
}

.el-statistic {
  --el-statistic-content-font-size: 30px;
}

.statistic-card {
  height: 60%;
  padding: 20px;
  border-radius: 4px;
  background-color: var(--el-bg-color-overlay);
}

.el-row {
  margin-bottom: 20px;
}
</style>