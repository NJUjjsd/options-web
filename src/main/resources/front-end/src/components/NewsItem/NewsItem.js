/**
 * Created by john on 2017/8/12.
 */
import React from 'react';
import { connect } from 'dva';
import styles from './NewsItem.css';

function NewsItem() {
  return (
    <div className={styles.news_item}>
      <div className={styles.news_head}>郑煤机(00564)重组拟购资产已通过中国商务部反垄断审查 A股继续停牌</div>
      <div className={styles.news_news_info}>
        <span>新浪财经网 </span>
        <span>2017-08-04 11:15</span>
      </div>
      <div className={styles.news_brief}>
        以下是概要／新闻开头 Type something Type something Type something Type something Type
        something Type something Type something Type something Type something Type something
        Type something Type something...
      </div>
      <hr className={styles.news_separate} />
    </div>
  );
}

export default connect()(NewsItem);
