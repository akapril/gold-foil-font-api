<template>
  <el-form :model="settings" label-width="120px" class="settings-form">
    <!-- 文本内容 -->
    <el-form-item label="文本内容">
      <el-input
          v-model="settings.text"
          type="textarea"
          rows="1"
          placeholder="请输入文本内容"
      />
    </el-form-item>

    <!-- 字体颜色 -->
    <el-form-item label="字体颜色">
      <el-select v-model="settings.fontColorType" placeholder="选择颜色, 默认是金色">
        <el-option label="金色" value="gold"/>
        <el-option label="银色" value="silver"/>
        <el-option label="黑色" value="black"/>
        <el-option label="黑色渐变" value="blackGradient"/>
      </el-select>
    </el-form-item>

    <!-- 背景 -->
    <el-form-item label="背景">
      <el-switch
          v-model="settings.isBackground"
          active-text="有"
          inactive-text="无"
          class="switch-margin"
      />
    </el-form-item>

    <!-- 渐变方向 -->
    <el-form-item label="渐变方向">
      <el-select v-model="settings.gradientPos" placeholder="选择方向, 默认是随机">
        <el-option label="从左到右" value="leftToRight"/>
        <el-option label="从上到下" value="topToBottom"/>
        <el-option label="从左上到右下" value="leftTopToRightBottom"/>
        <el-option label="从左下到右上" value="leftBottomToRightTop"/>
        <el-option label="从右到左" value="rightToLeft"/>
        <el-option label="从下到上" value="bottomToTop"/>
        <el-option label="从右上到左下" value="rightTopToLeftBottom"/>
        <el-option label="从右下到左上" value="rightBottomToLeftTop"/>
        <el-option label="圆形" value="circular"/>
        <el-option label="圆形随机" value="circularRandom"/>
        <el-option label="随机" value="random"/>
      </el-select>
    </el-form-item>

    <!-- 按钮 -->
    <el-form-item>
      <el-button type="primary" @click="generate('image')" class="apply-btn">生成图片</el-button>
      <el-button type="primary" @click="generate('svg')" class="apply-btn">生成SVG</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
const props = defineProps({
  settings: {
    type: Object,
    required: true,
  },
});

const emit = defineEmits(['update:settings', 'generate']); // 定义事件

// 触发不同的生成事件
const generate = (type) => {
  emit('generate', type); // 将生成类型发送到父组件
};
</script>

<style lang="less" scoped>
@import url('@/styles/globalVariable');

.settings-form {
  max-width: 800px;
  margin: 0 auto;
  background: @Global-color-gradient; /* 银色渐变 */
  border-radius: @Global-radius;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 20px;
}


// 修改el-switch样式
.settings-form ::v-deep(.el-switch__label) {
  color: #888888; /* 自定义字体颜色 */

  &.is-default {
    color: #dedede; /* 自定义默认时的字体颜色 */
  }

  &.is-active {
    color: #ffffff; /* 自定义选中时的字体颜色 */
  }
}

.switch-margin {
  --el-switch-border-color: none; /* 边框颜色 */
  --el-switch-on-color: #999999; /* 开启状态的背景颜色 */
  --el-switch-off-color: #d3d3d3; /* 关闭状态的背景颜色 */
}

.apply-btn {
  color: @Global-btn-color;
  background-color: @Global-btn-background;
  font-size: 16px;
  border-radius: @Global-radius;
  padding: 10px 20px;
  transition: background-color 0.3s;
  // 边框颜色为无色
  border: none;
}

.apply-btn:hover {
  background-color: @Global-btn-background-hover;
  color: @Global-btn-color-hover;
}

.apply-btn:active {
  background-color: @Global-btn-background-active;
  // 鼠标按下不显示边框
  outline: none;
}

</style>
