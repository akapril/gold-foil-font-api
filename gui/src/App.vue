<template>
  <div class="app-container">
    <el-row :gutter="20" class="mt-20">
      <el-col :span="24">
        <setting-form
            v-model:settings="settings"
            @update:settings="generateContent"
            @generate="handleGenerate"
        />
      </el-col>
    </el-row>

    <PreviewArea :settings="settings" :imageUrl="imageUrl" :svgContent="svgContent"/>
  </div>
</template>

<script setup>
import {ref} from 'vue';
import {generateGoldFoilImage, generateGoldFoilSVG} from './api/goldFoilApi';
import SettingForm from './components/SettingsPanel/index.vue';
import PreviewArea from "./components/PreviewArea/index.vue";
import {ElLoading, ElMessage} from 'element-plus';
import {defaultValue} from "@/data/defaultValue.js";

const settings = ref({
  text: '',
  fontColorType: defaultValue.fontColorType,
  isBackground: defaultValue.isBackground,
  gradientPos: defaultValue.gradientPos,
});

const imageUrl = ref('');
const svgContent = ref('');

const generateContent = async (type) => {

  // 校验用户是否输入了文本
  if (!settings.value.text) {
    ElMessage.warning('请输入要生成的文本！');
    return;
  }

  // 显示加载动画
  const loading = ElLoading.service({
    lock: true,
    text: '生成中...',
    background: 'rgba(0, 0, 0, 0.7)',
  });

  try {
    const params = {
      text: settings.value.text,
      gradientPos: settings.value.gradientPos,
      fontColorType: settings.value.fontColorType,
      isBackground: settings.value.isBackground.toString(),
    };
    console.log('params:', params)

    if (type === 'image') {
      // 调用生成图片的 API
      imageUrl.value = await generateGoldFoilImage(params);
      console.log('imageUrl:', typeof imageUrl.value);
    } else if (type === 'svg') {
      // 调用生成SVG的 API
      svgContent.value = await generateGoldFoilSVG(params);
      console.log('svgContent:', typeof svgContent.value);
    }
  } catch (error) {
    ElMessage.error('生成内容失败，请检查输入或稍后重试！');
    console.error(error);
  } finally {
    loading.close();
  }
};

// 处理 generate 事件，根据类型调用不同的生成函数(svg或者image)
const handleGenerate = (type) => {
  generateContent(type);
};
</script>

<style lang="less" scoped>
@import "@/styles/globalVariable";

.app-container {
  // 银色渐变上中下
  background: linear-gradient(111deg, #f5f5f5, #d2d2d2, #f5f5f5);
  padding: 35px;
  border-radius: @Global-radius;
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
</style>
