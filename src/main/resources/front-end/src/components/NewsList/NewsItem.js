/**
 * Created by john on 2017/8/12.
 */
import React from 'react';
import { connect } from 'dva';
import styles from './NewsItem.css';

function NewsItem({ dispatch, data }) {
  function toDetail() {
    dispatch({
      type: 'news/putDetail',
      payload: { data },
    });
  }

  let paragraph = data.text;
  if (paragraph.length > 160) {
    paragraph = `${paragraph.substring(0, 160)}...`;
  }

  return (
    <div onClick={toDetail.bind(this)} className={styles.news_container}>
      <div className={styles.news_head}>{data.title}</div>
      <div className={styles.news_info}>{data.dateToString}</div>
      <div className={styles.news_brief}>{paragraph}</div>
      <hr className={styles.news_separate} />
    </div>
  );
}

export default connect()(NewsItem);
