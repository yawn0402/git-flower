<script setup>
import { ref } from 'vue';
import { useBuyerStore, useTokenStore } from "@/stores/index.js"
const buyerStore = useBuyerStore()
const tokenStore = useTokenStore()
const nowAvaterUrl = ref(buyerStore.buyerInfo.bavatar)

import { ElMessage } from 'element-plus';
const uploadSuccess = (result) => {

  if (result.code === 0) {
    ElMessage.success("上传成功")
    nowAvaterUrl.value = result.data;
  } else {
    ElMessage.error(result.message)
  }


}

const uploadFail = () => {
  ElMessage.error(result.data.message)
}
import { userAvatarUpdateService } from "@/api/buyer.js"
const resetAvatar = async () => {
  const result = await userAvatarUpdateService(nowAvaterUrl.value)
  buyerStore.setAvatar(nowAvaterUrl)
  ElMessage.success("修改成功")
}
</script>

<template>
  <el-row class="login-page">
    <el-col :span="6" :offset="9" class="form">
      <el-form >
        <el-form-item><h1 class="title">点击图像上传</h1></el-form-item>
        <el-form-item>

          <!-- 
                        auto-upload:设置是否自动上传
                        action:设置服务器接口路径
                        name:设置上传的文件字段名
                        headers:设置上传的请求头
                        on-success:设置上传成功的回调函数
                     -->
          <el-upload class="avatar-uploader" :auto-upload="true" :show-file-list="false" action="/api/upload"
            name="file" :headers="{ 'Authorization': tokenStore.token }" :on-success="uploadSuccess"
            :on-error="uploadFail">
            <img v-if="nowAvaterUrl" :src="nowAvaterUrl" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon">
              <Plus />
            </el-icon>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <div style="width: 100%; text-align: center;">
            <el-button style="width: 100%;" type="primary" auto-insert-space @click="resetAvatar()">
              确定
            </el-button>
          </div>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<style lang="scss" scoped>
.login-page {
  height: 100%;
  background-color: #fff;

  .form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    user-select: none;

    .title {
      margin: 0 auto;
    }

    .button {
      width: 100%;
    }

    .flex {
      width: 100%;
      display: flex;
      justify-content: space-between;
    }

    .avatar-uploader {
      :deep() {
        .avatar {
          width: 248px;
          height: 248px;
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
    }
  }
}
</style>