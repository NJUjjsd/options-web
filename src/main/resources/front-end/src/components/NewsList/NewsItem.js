/**
 * Created by john on 2017/8/12.
 */
import React from 'react';
import { connect } from 'dva';
import styles from './NewsItem.css';

function NewsItem({ data }) {
  return (
    <div>
      <div className={styles.news_head}>{data.title}</div>
      <div className={styles.news_info}>{data.dateToString}</div>
      <div className={styles.news_brief}>{data.text}</div>
      <hr className={styles.news_separate} />
    </div>
  );
}

export default connect()(NewsItem);
