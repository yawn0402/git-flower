<script setup>
import { ref, } from 'vue';
import { Edit, Delete, Plus } from '@element-plus/icons-vue'
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
const visibleDrawer = ref(false)
const visiblePicDrawer = ref(false)
const total = ref(20)
const pageNum = ref(1)
const pageSize = ref(4)

//导入token
import { useTokenStore } from '@/stores/index.js';
const tokenStore = useTokenStore();
const suppliersList = ref([
  {
    sid: 1,
    sname: 'tomorin'
  },
  {
    sid: 2,
    sname: 'soyo'
  },
])


const flowerList = ref([
  {
    fid: 1,
    fname: 'f1',
    fprice: 23,
    fnum: 32,
    fcover: 'https://big-event-ct.oss-cn-beijing.aliyuncs.com/a7031a18-d6e7-4176-afa4-505c6b386682.png',
    sname: 'tomorin',
    fintroduction: '美丽的花'
  },
  {
    fid: 2,
    fname: 'f2',
    fprice: 23,
    fnum: 32,
    fcover: 'https://big-event-ct.oss-cn-beijing.aliyuncs.com/a7031a18-d6e7-4176-afa4-505c6b386682.png',
    sname: 'tomorin',
    fintroduction: '美丽的花'
  },
  {
    fid: 1,
    fname: 'f1',
    fprice: 23,
    fnum: 32,
    fcover: 'https://big-event-ct.oss-cn-beijing.aliyuncs.com/a7031a18-d6e7-4176-afa4-505c6b386682.png',
    sname: 'tomorin',
    fintroduction: '美丽的花'
  },
  {
    fid: 2,
    fname: 'f2',
    fprice: 23,
    fnum: 32,
    fcover: 'https://big-event-ct.oss-cn-beijing.aliyuncs.com/a7031a18-d6e7-4176-afa4-505c6b386682.png',
    sname: 'tomorin',
    fintroduction: '美丽的花'
  },
])


const searchFname = ref('')
const searchSid = ref('')
import {
  suppliersListService,
  flowerListService,
  suppliersAddService,
  flowerPurchaseService,
  flowerDeleteService,
  flowerNewService,
  flowerEditService,
  picsAddService,
  picDeleteService
} from "@/api/saller.js"
import { ElMessage,ElMessageBox } from 'element-plus';
// ------------获取初始数据------------
const getflowerListAndSipList = async () => {

  const result1 = await suppliersListService()
  suppliersList.value = result1.data
  const result2 = await flowerListService(pageNum.value, searchFname.value, searchSid.value)
  flowerList.value = result2.data.items
  total.value = result2.data.total


  for (let i = 0; i < flowerList.value.length; i++) {
    let flower = flowerList.value[i]
    for (let j = 0; j < suppliersList.value.length; j++) {
      if (flower.sid == suppliersList.value[j].sid) {
        flower.sname = suppliersList.value[j].sname
      }
    }
  }
}
getflowerListAndSipList()



const supplerModel = ref(
  {
   sname:'',
   stel:''
  }
)


const supplerDialogVisiable=ref(false)
const flowerAddDialogVisiable=ref(false)
const purchaseModel=ref({
  fid:'',
  sid:'',
  sname:'',
  purprice:1,
  purnum:1
})


const addSuppler = async () => {
  if(supplerModel.value.sname==''||supplerModel.value.stel==''){
    ElMessage.error("不能为空哦") 
  }else{
    const result=await suppliersAddService(supplerModel.value)
    getflowerListAndSipList()

    // console.log(supplerModel.value)
    ElMessage.success("添加成功")
    supplerModel.value.sname=''
    supplerModel.value.stel=''
  }
}

const purchaseFlower = async () => {

  for (let j = 0; j < suppliersList.value.length; j++) {
      if (purchaseModel.value.sname == suppliersList.value[j].sname) {
        purchaseModel.value.sid = suppliersList.value[j].sid
        break
      }
    }
    // console.log(purchaseModel.value)
  const result=await flowerPurchaseService(purchaseModel.value)
  getflowerListAndSipList()
  ElMessage.success("补货成功")
  

}
const deleteFlower = async(fid) => {
  ElMessageBox.confirm(
    '你确认删除该花卉吗？',
    '温馨提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(async () => {
      
      const result = await flowerDeleteService(fid)
      getflowerListAndSipList()
      ElMessage({
        type: 'success',
        message: '删除成功',
      })
    })
    .catch(() => {
      //用户点击了取消
      
      ElMessage({
        type: 'info',
        message: '取消成功',
      })
    })
 
}






// -------------抽屉------------
const isAddOperate =ref(true)
import {Close } from '@element-plus/icons-vue'
const flowerModel = ref(
  {
    fid: "",
    fname: "",
    fprice: 1,
    fnum: 0,
    fcover: "",
    sname: "",
    sid:'',
    fintroduction: ''
  }
)

const pics =ref([
  {
    pid:1,
    paddress:'https://big-event-ct.oss-cn-beijing.aliyuncs.com/a76d6b81-fd33-42f2-acc1-132a63237ca6.png'
  },
  {
    pid:2,
    paddress:'https://big-event-ct.oss-cn-beijing.aliyuncs.com/b4e6b481-2dd5-4fdb-a7b3-9d8e1adb89e2.jpg'
  },
  {
    pid:3,
    paddress:'https://big-event-ct.oss-cn-beijing.aliyuncs.com/a76d6b81-fd33-42f2-acc1-132a63237ca6.png'
  },
  {
    pid:4,
    paddress:'https://big-event-ct.oss-cn-beijing.aliyuncs.com/b4e6b481-2dd5-4fdb-a7b3-9d8e1adb89e2.jpg'
  },
  {
    pid:5,
    paddress:'https://big-event-ct.oss-cn-beijing.aliyuncs.com/a76d6b81-fd33-42f2-acc1-132a63237ca6.png'
  },
  {
    pid:6,
    paddress:'https://big-event-ct.oss-cn-beijing.aliyuncs.com/b4e6b481-2dd5-4fdb-a7b3-9d8e1adb89e2.jpg'
  },
])

const picsList =ref([
'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg',
'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg',
])
const getpicsList=()=>{
  for(let i =0;i<pics.value.length;i++){
    picsList.value[i]=pics.value[i].paddress
  }
  // console.log(picsList.value)
}




const clearFlowerModel=()=>{
  flowerModel.value={
    fid: '',
    fname: '',
    fprice: 1,
    fnum: 0,
    fcover: '',
    sname: '',
    sid:'',
    fintroduction: ''
  }
  getflowerListAndSipList()
}

const flowerNew=async()=>{
  if(flowerModel.value.fname==''){
    ElMessage.error("花卉名不能为空哦")
  }else if(flowerModel.value.sid==''){
    ElMessage.error("供应商不能为空哦")
  }
  // else if(flowerModel.value.fcover==''){
  //   ElMessage.error("封面不能为空哦")
  // }
  else if( flowerModel.value.fintroduction==''){
    ElMessage.error("介绍不能为空哦")
  }else{
    // console.log(flowerModel.value)

    const result=await flowerNewService(flowerModel.value)
    getflowerListAndSipList()
    visibleDrawer.value=false
    clearFlowerModel()
    ElMessage.success("添加成功")
  }
}



const coverUploadSuccess = (result) => {

if (result.code === 0) {
flowerModel.value.fcover=result.data
  ElMessage.success("上传成功")
 
} else {
  ElMessage.error(result.message)
}


}
const openFlowerBaseInfo=(fid)=>{
 
  isAddOperate.value=false
  for(let i=0;i<flowerList.value.length;i++){
      if(flowerList.value[i].fid==fid){
        flowerModel.value=flowerList.value[i]
        break
      }
  }
  visibleDrawer.value=true
}
const flowerEdit=async()=>{
  if(flowerModel.value.fname==''){
    ElMessage.error("花卉名不能为空哦")
  }else if(flowerModel.value.sid==''){
    ElMessage.error("供应商不能为空哦")
  }
  // else if(flowerModel.value.fcover==''){
  //   ElMessage.error("封面不能为空哦")
  // }
  else if( flowerModel.value.fintroduction==''){
    ElMessage.error("介绍不能为空哦")
  }else{
    // console.log(flowerModel.value)
    const result=await flowerEditService(flowerModel.value)
    getflowerListAndSipList()
    visibleDrawer.value=false
    clearFlowerModel()
    ElMessage.success("添加成功")
  }
}


import{flowerPicsService}from"@/api/flower.js"
const getpics=async()=>{
  const result=await flowerPicsService(flowerModel.value.fid)
  pics.value=result.data
  getpicsList()
}


const picUploadSuccess =async (result) => {


  // console.log(result.data)
if (result.code === 0) {
  const picModel=ref(
   {
    fid:flowerModel.value.fid,
    paddress:result.data
   } 
  )
  const result1=await picsAddService(picModel.value)
  
  getpics()
  ElMessage.success("上传成功")
 
} else {
  ElMessage.error(result.message)
}


}


const uploadFail = (result) => {
ElMessage.error(result.data.message)
}

const deletePic = (pid) => {

  ElMessageBox.confirm(
    '你确认删除该图片吗？',
    '温馨提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(async () => {
      
      const result = await picDeleteService(pid)
      getpics()
      ElMessage({
        type: 'success',
        message: '删除成功',
      })
    })
    .catch(() => {
      //用户点击了取消
      
      ElMessage({
        type: 'info',
        message: '取消成功',
      })
    })
}


</script>

<template>


  <el-card class="page-container ">

  <!-- 顶部 -->
    <template #header>
      <div class="header">
        <div style="display: flex;">
          <el-input v-model="searchFname"></el-input>
          <el-select placeholder="选择供应商" v-model="searchSid" style="width: 250px;">
            <el-option v-for="c in suppliersList" :key="c.sid" :label="c.sname" :value="c.sid">
            </el-option>
          </el-select>
          <el-button type="primary" @click="getflowerListAndSipList()">搜索</el-button>
          <el-button type="default" @click="searchFname = ''; searchSid = ''; getflowerListAndSipList()">重置</el-button>
        </div>
        <div style="display: flex;">
          <el-button type="primary" @click="supplerDialogVisiable = true">添加供应商</el-button>
          <el-button type="success" @click="visibleDrawer=true ;isAddOperate=true">新进花卉</el-button>
        </div>
      </div>
    </template>


    <!-- 展示表格 -->
    <el-table class="dataTable" :data="flowerList" style="width: 100% ">
      <el-table-column label="编号" width="70" prop="fid"></el-table-column>
      <el-table-column label="花名" width="120" prop="fname"></el-table-column>
      <el-table-column label="供应商" width="120" prop="sname"></el-table-column>
      <el-table-column label="封面" width="100">
        <template #default="{ row }">
          <img :src=row.fcover class="image" />
        </template>
      </el-table-column>
      <el-table-column label="库存" prop="fnum" width="70"> </el-table-column>
      <el-table-column label="售价" prop="fprice" width="70"></el-table-column>
      <el-table-column label="描述" prop="fintroduction"></el-table-column>
      <el-table-column label="操作" width="200">
        <template #default="{ row }">
          <el-button @click="flowerAddDialogVisiable=true;purchaseModel.fid=row.fid;purchaseModel.sname=row.sname" circle plain type="success">补货 </el-button>
          <el-button @click="openFlowerBaseInfo(row.fid) " plain type="primary">基本信息</el-button>
          <el-button @click="deleteFlower(row.fid)" :icon="Delete" circle plain type="danger"></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据" />
      </template>
    </el-table>

    <!-- 分页条 -->
    <el-pagination small background layout="jumper, total,  prev, pager, next" :total="total" @current-change=" getflowerListAndSipList()"
      :page-size=pageSize v-model:current-page="pageNum" style="margin-top: 20px; justify-content: center" />
  </el-card>


<!-- 添加供应商 -->
  <el-dialog title="添加供应商" v-model="supplerDialogVisiable" width="30%">
    <el-row>
      <el-col :span="6" :offset="3"><span><label>供应商名:</label></span></el-col>
      <el-col :span="13"><el-input v-model="supplerModel.sname"></el-input></el-col>
    </el-row>
  
    <el-row>
      <el-col :span="6" :offset="3"><span><label>联系方式:</label></span></el-col>
       <el-col :span="13"><el-input v-model="supplerModel.stel"></el-input></el-col>
    </el-row>
  
    <template #footer>
      <span class="dialog">
        <el-button type="primary" @click="addSuppler(); supplerDialogVisiable = false">确定</el-button>
        <el-button type="warning" @click="supplerDialogVisiable = false;supplerModel.sname = ''; supplerModel.stel=''">取消</el-button>
      </span>
    </template>
  </el-dialog>


  <!-- 花卉补货 -->
  <el-dialog title="花卉补货" v-model="flowerAddDialogVisiable" width="30%">
    <el-row>
      <el-col :span="6" :offset="3"><span><label>补货数量:</label></span></el-col>
      <el-col :span="13"><el-input-number size="small" v-model="purchaseModel.purnum" min="1" max="999"></el-input-number ></el-col>
    </el-row>
  
    <el-row>
      <el-col :span="6" :offset="3"><span><label>进货单价:</label></span></el-col>
       <el-col :span="13"><el-input-number size="small" v-model="purchaseModel.purprice" min="1" max="999"></el-input-number ></el-col>
    </el-row>
  
    <template #footer>
      <span class="dialog">
        <el-button type="primary" @click="purchaseFlower(); flowerAddDialogVisiable = false">确定</el-button>
        <el-button type="warning" 
        @click="flowerAddDialogVisiable = false;purchaseModel.purnum=1;
         purchaseModel.purprice=1;purchaseModel.fid='';purchaseModel.sname=''">取消</el-button>
      </span>
    </template>
  </el-dialog>


  <!-- 花卉抽屉 -->
  <el-drawer v-model="visibleDrawer" :title="isAddOperate?'新增花卉':'基本信息'" direction="rtl" size="50%">
    <el-form  label-width="100px" v-model="flowerModel">
      <el-form-item label="花卉名">
        <el-input v-model="flowerModel.fname"></el-input>
      </el-form-item>
      <el-form-item label="售价">
        <el-input-number  min="1" max="999" v-model="flowerModel.fprice"></el-input-number>
      </el-form-item>
      <el-form-item label="供应商" v-if="isAddOperate" >
        <el-select placeholder="请选择" v-model="flowerModel.sid">
          <el-option v-for="c in suppliersList" :key="c.sid" :label="c.sname" :value="c.sid">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="花卉图片" v-else>
        <el-link style="color: plum;" @click="visiblePicDrawer = true;getpics()">
          点击管理</el-link>
      </el-form-item>
      <el-form-item label="花卉封面">

        <el-upload class="avatar-uploader" :auto-upload="true" :show-file-list="false" action="/api/upload" name="file"
          :headers="{ 'Authorization': tokenStore.token }" :on-success="coverUploadSuccess" :on-error="uploadFail">
          <img v-if="flowerModel.fcover" :src="flowerModel.fcover" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon">
            <Plus />
          </el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="花卉介绍">
        <div class="editor">
          <quill-editor theme="snow"  contentType="html" v-model:content="flowerModel.fintroduction">
          </quill-editor>
        </div>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="isAddOperate?flowerNew():flowerEdit()">确定</el-button>
        <el-button type="info" @click="clearFlowerModel();visibleDrawer=false">取消</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>




   <!-- 图片抽屉 -->
   <el-drawer v-model="visiblePicDrawer" title="图片管理" direction="ltl" size="100%" >
   <el-row v-for="url in pics"  :key="url.pid">
    <div class="demo-image__lazy">
    <!-- <el-image style="display: block;width: 500px; padding: 20px;" :src="url.paddress" lazy /> -->
    <el-image
      style="display: block;width: 500px; padding: 20px;"
      :src="url.paddress"
      :zoom-rate="1.2"
      :preview-src-list="picsList"
      :initial-index="0"
      fit="cover"
    />
      <el-button :icon="Close" type="danger" plain style="display:block;margin:0 auto"
      @click="deletePic(url.pid)"></el-button>
  </div>
   </el-row>
   <el-row>
    <span>
      上传图片:
    </span>
   </el-row>
  
   <el-upload class="avatar-uploader" :auto-upload="true" :show-file-list="false" action="/api/upload" name="file"
          :headers="{ 'Authorization': tokenStore.token }" :on-success="picUploadSuccess" :on-error="uploadFail">
          <el-icon  class="avatar-uploader-icon">
            <Plus />
          </el-icon>
        </el-upload>
  
  </el-drawer>
</template>




<style lang="scss" scoped>
.page-container {
  min-height: 100%;
  box-sizing: border-box;
  justify-content: space-between;

  .image {
    width: 50px;
    display: block;
  }

  .header {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
}
.dialog {
  display: flex;
  
  justify-content: center;
  
}
.el-row {
  margin-bottom: 20px;
}
/* 抽屉样式 */
.avatar-uploader {
  
  :deep() {
    .avatar {
      width: 178px;
      height: 178px;
      display: block;
    }

    .el-upload {
      border: 1px dashed var(--el-border-color);
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
      transition: var(--el-transition-duration-fast);
    }

    .el-upload:hover {
      border-color: var(--el-color-primary);
    }

    .el-icon.avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 178px;
      height: 178px;
      text-align: center;
    }
  }
  .demo-image__lazy {
  height: 400px;
  overflow-y: auto;
  text-align:center
 
}
.demo-image__lazy .el-image {
  display: block;
  max-width: 100px;
  margin-bottom: 10px;
}
.demo-image__lazy .el-image:last-child {
  margin-bottom: 0;
}
}

.editor {
  width: 100%;

  :deep(.ql-editor) {
    min-height: 200px;
  }
}
</style>