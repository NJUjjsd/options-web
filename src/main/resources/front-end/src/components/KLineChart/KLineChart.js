/**
 * Created by a297 on 17/8/14.
 */
import React from 'react';
import { connect } from 'dva';

const echarts = require('echarts/lib/echarts');
require('echarts/lib/chart/candlestick');
require('echarts/lib/component/tooltip');
require('echarts/lib/chart/line'); // 图表类型
require('echarts/lib/chart/bar');
require('echarts/lib/component/legend');
require('echarts/lib/component/toolbox');
require('echarts/lib/component/brush');
require('echarts/lib/component/grid');
require('echarts/lib/component/grid');
require('echarts/lib/component/dataZoom');

class KLineChart extends React.Component {

  componentDidMount() {
    this.drawLineChart();
  }

  componentDidUpdate() {
    this.drawLineChart();
  }

  drawLineChart() {
    const rawData = this.props.data;
    const myChart = echarts.init(this.ref);
    myChart.setOption(getOption(rawData));
  }

  render() {
    return <div ref={(c) => { this.ref = c; }} style={{ width: '100%', height: '580px', margin: '30px 0 100px 0' }} />;
  }
}
function getOption(rawData) {
  const data = splitData(rawData);
  const option = {
    backgroundColor: '#eee',
    animation: false,
    legend: {
      bottom: 10,
      left: 'center',
      data: ['Dow-Jones index', 'MA5', 'MA10', 'MA20', 'MA30'],
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'cross',
      },
      confine: true,
      backgroundColor: 'rgba(245, 245, 245, 0.8)',
      borderWidth: 1,
      borderColor: '#ccc',
      padding: 10,
      textStyle: {
        color: '#000',
      },
      extraCssText: 'width: 208px',
    },
    axisPointer: {
      link: { xAxisIndex: 'all' },
      label: {
        backgroundColor: '#777',
      },
    },
    toolbox: {
      feature: {
        dataZoom: {
          yAxisIndex: false,
        },
        brush: {
          type: ['lineX', 'clear'],
        },
      },
    },
    brush: {
      xAxisIndex: 'all',
      brushLink: 'all',
      outOfBrush: {
        colorAlpha: 0.1,
      },
    },
    grid: [
      {
        left: '10%',
        right: '8%',
        height: '50%',
      },
      {
        left: '10%',
        right: '8%',
        top: '63%',
        height: '16%',
      },
    ],
    xAxis: [
      {
        type: 'category',
        data: data.categoryData,
        scale: true,
        boundaryGap: false,
        axisLine: { onZero: false },
        splitLine: { show: false },
        splitNumber: 20,
        min: 'dataMin',
        max: 'dataMax',
        axisPointer: {
          z: 100,
        },
      },
      {
        type: 'category',
        gridIndex: 1,
        data: data.categoryData,
        scale: true,
        boundaryGap: false,
        axisLine: { onZero: false },
        axisTick: { show: false },
        splitLine: { show: false },
        axisLabel: { show: false },
        splitNumber: 20,
        min: 'dataMin',
        max: 'dataMax',
      },
    ],
    yAxis: [
      {
        scale: true,
        splitArea: {
          show: true,
        },
      },
      {
        scale: true,
        gridIndex: 1,
        splitNumber: 2,
        axisLabel: { show: false },
        axisLine: { show: false },
        axisTick: { show: false },
        splitLine: { show: false },
      },
    ],
    dataZoom: [
      {
        type: 'inside',
        xAxisIndex: [0, 1],
        start: 50,
        end: 100,
      },
      {
        show: true,
        xAxisIndex: [0, 1],
        type: 'slider',
        top: '85%',
        start: 50,
        end: 100,
      },
    ],
    series: [
      {
        name: 'Dow-Jones index',
        type: 'candlestick',
        data: data.values,
        itemStyle: {
          normal: {
            borderColor: null,
            borderColor0: null,
          },
        },
        // tooltip: {
        // formatter: function (param) {
        //   param = param[0];
        //   return [
        //     'Date: ' + param.name + '<hr size=1 style="margin: 3px 0">',
        //     'Open: ' + param.data[0] + '<br/>',
        //     'Close: ' + param.data[1] + '<br/>',
        //     'Lowest: ' + param.data[2] + '<br/>',
        //     'Highest: ' + param.data[3] + '<br/>',
        //   ].join('');
        // },
      },
      {
        name: 'MA5',
        type: 'line',
        data: calculateMA(5, data),
        smooth: true,
        lineStyle: {
          normal: { opacity: 0.5 },
        },
      },
      {
        name: 'MA10',
        type: 'line',
        data: calculateMA(10, data),
        smooth: true,
        lineStyle: {
          normal: { opacity: 0.5 },
        },
      },
      {
        name: 'MA20',
        type: 'line',
        data: calculateMA(20, data),
        smooth: true,
        lineStyle: {
          normal: { opacity: 0.5 },
        },
      },
      {
        name: 'MA30',
        type: 'line',
        data: calculateMA(30, data),
        smooth: true,
        lineStyle: {
          normal: { opacity: 0.5 },
        },
      },
      {
        name: 'Volume',
        type: 'bar',
        xAxisIndex: 1,
        yAxisIndex: 1,
        data: data.volumes,
      },
    ],
  };
  return option;
}

/**
 * 初始化数据。
 * @param rawData 原始数据
 * @returns {{categoryData: Array, values: Array, volumes: Array}}
 * 日期列表；
 * 按日期顺次排列的值列表: [开盘, 收盘, 最低, 最高, 部分成交]；
 * 按日期顺次排列的成交量列表: [第几天, 部分成交, ± 1]
 */
function splitData(rawData) {
  const categoryData = [];
  const values = [];
  const volumes = [];

  for (let i = 0; i < rawData.length; i += 1) {
    // 取出日期
    // e.g.["2004-01-16",10556.37,10600.51,10503.7,10666.88,254170000]
    // －－ splice(0, 1) －－ >
    // 剩[10556.37,10600.51,10503.7,10666.88,254170000]
    // 返回["2004-01-16"]
    categoryData.push(rawData[i].splice(0, 1)[0]);
    values.push(rawData[i]);
    volumes.push([i, rawData[i][4]]);
  }

  return { categoryData, values, volumes };
}

/**
 * 计算均值。
 * @param dayCount 5 ｜ 10 ｜ 20 ｜ 30
 * @param data
 * @returns {Array}
 */
function calculateMA(dayCount, data) {
  const result = [];
  for (let i = 0, len = data.values.length; i < len; i += 1) {
    if (i < dayCount) {
      result.push('-');
    }
    if (i >= dayCount) {
      let sum = 0;
      for (let j = 0; j < dayCount; j += 1) {
        sum += data.values[i - j][1];      // i=dayCount
      }
      result.push(+(sum / dayCount).toFixed(3));  // 四舍五入保留三位
    }
  }
  return result;
}

function mapStateToProps(state) {
  const props = state.market;
  const data = props.ETFKLineRawData;
  return { data };
}

export default connect(mapStateToProps)(KLineChart);
