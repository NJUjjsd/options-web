/**
 * Created by a297 on 17/8/12.
 */
import React from 'react';
import { connect } from 'dva';
import { Row, Col } from 'antd';
import styles from './NewsBody.css';

function NewsBody() {
  return (
    <Row>
      <Col offset={4} span={15}>
        <div className={styles.news_title}>
          <p className={styles.news_heading}>
            郑煤机(00564)重组拟购资产已通过中国商务部反垄断审查 A股继续停牌
          </p>
          <p className={styles.news_source}>
            新浪财经网 2017-08-04 11:15
          </p>
        </div>
        <hr className={styles.below_title} />
        <div className={styles.news_article}>
          <div>
            <span>在今年上半年针对银行同业业务、通道业务的金融去杠杆的措施初显成效之后，</span>
            <span>下半年去杠杆任务进入更加“本质化”的阶段，第五次全国金融工作会议强调“推动经济去杠杆，把国有企业降杠杆作为重中之重”。</span>
            <span>事实上，债转股正是目前实现国企降杠杆的重要途径之一。</span>
          </div>
          <div>
            近日，银监会召开2017年年中工作座谈会提出，“下半年银行业服务实体经济要有新贡献，积极推动债转股落地实施，有效支持去产能、去杠杆。
          </div>
        </div>
      </Col>
    </Row>
  );
}

export default connect()(NewsBody);
