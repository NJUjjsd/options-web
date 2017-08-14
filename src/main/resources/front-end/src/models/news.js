/**
 * Created by a297 on 17/8/13.
 */

import * as newsDetailsService from '../services/newsDetails';

export default {

  namespace: 'NewsDetails',

  state: {
    heading: '初始的state——heading',
    source: '',
    paragraphs: '公司发布2017年上半年经营数据,新签合同额11955亿元,同增33.7%;国内新签合同额10837亿元,同增30.2%;海外新签合同额1117亿元,同增80.5%;地产业务合约销售额1223亿元,同增50.0%。点评如下:\r\n   \r\nPPP拉动基建订单高增长,产业转型持续推进\r\n   \r\n公司上半年房屋建筑、基础设施、勘察设计新签合同额分别为8234、3667和53亿元,地产业务合约销售额1223亿元,产业转型持续推进,房建、基建、地产新签合同占比分别为63%、29%、9%,逐步向公司“532产业结构计划”靠拢。基础设施业务同比增长64.5%,PPP模式是公司基建订单高速增长的主要推动力,2016年PPP带动的基建增量订单达50%,2017年一季度PPP带动的基建增量订单已达七成,占比不断提高。在财政部接连发布50号、62号和87号文,全面规范地方政府的违规举债问题。此背景下,地方政府需要一个学习文件过程,订单落地普遍较慢,5、6月后恢复正常,央企由于手续规范、可调配资源较多,订单迅速向央企集中,公司6月份新签合同额出现了快速提升,金额达4143亿元,占上半年新签合同额的34.7%,同增85.0%。\r\n   \r\n海外订单增速加快,多元融资助力拓展\r\n   \r\n作为最早涉足海外业务的建筑央企之一,公司持续深耕海外市场,已有13家子公司获授海外业务许可权,已在53个国家正式开展业务。近几年公司海外业务比重逐年上升,上半年新签海外合同额占比已达总合同额的9.3%,“十三五”期间达到10%的目标可期。公司近期成功发行总共10亿美元的境外美元债券用于境外项目及境外一般公司用途,有利于进一步优化公司融资结构,加大对公司海外业务的资源支持。未来公司将进一步采用多元化的融资方式如发行境外美元债、中期票据等来进行低成本融资,助力海外业务健康稳健发展。\r\n   \r\n国改步伐加快,股权激励持续推进\r\n   \r\n近期国资委发布了一系列文件旨在转变国资委职能,下放权力,减少审批,提高效率。作为建筑央企国改的标杆企业,也是唯一一个进行了两次股权激励的建筑央企,政策的逐渐明朗叠加供给侧改革带来的周期行业的阶段性复苏,国企改革黄金窗口期显现。未来公司的第三期股权激励计划有望落地,将惠及更多的项目骨干,净利率有进一步提升的空间。\r\n   \r\n投资建议\r\n   \r\n公司上半年新签合同额同比增速明显,在存量博弈的建筑行业,订单、信贷向央企集中的趋势愈发明朗,作为行业龙头央企,公司将充分受益。此外,PPP对基建的拉动、“一带一路”对海外业务的推进以及国改对经营效率的提升,我们持续看好公司的未来发展。预计2017~2019年EPS为1.14、1.29、1.44元/股,对应PE为9、8、7倍,维持“买入”评级。\r\n   \r\n风险提示:固定资产投资额增速持续下滑;一带一路、PPP不及预期。',
    original: 'http://blog.csdn.net/lxhjh/article/details/51711148',
  },

  subscriptions: {
    setup({ dispatch, history }) {
      return history.listen(
        ({ pathname, query }) => {
          if (pathname === '/newsDetails') {
            dispatch({ type: 'fetchNewsDetailsByID', payload: query });
          }
        });
    },
  },

  effects: {
    *fetchNewsDetailsByID({ payload: { newsID = '000' } }, { call, put }) {
      const newsContent = yield call(newsDetailsService.getNewsDetailsByID, { newsID });
      yield put({
        type: 'saveNewsDetailsByID',
        payload: {
          heading: newsContent.heading,
          source: newsContent.source,
          paragraphs: newsContent.paragraphs,
          original: newsContent.original,
        },
      });
    },
  },

  reducers: {
    saveNewsDetailsByID(state, { payload: { heading, source, paragraphs, original } }) {
      return { ...state, heading, source, paragraphs, original };
    },
  },

};
