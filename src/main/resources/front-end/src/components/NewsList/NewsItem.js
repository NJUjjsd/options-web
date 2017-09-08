/**
 * Created by john on 2017/8/12.
 */
import React from 'react';
import { connect } from 'dva';
import styles from './NewsItem.css';

function NewsItem({ dispatch, data }) {
  let topState = styles.hideTop;
  if (data.top) {
    topState = styles.showTop;
  }
  function toDetail() {
    dispatch({
      type: 'news/putDetail',
      payload: { data },
    });
  }

  function getResolvedText() {
    let result = '';
    let total = 0;
    for (let i = 0; i < data.resolvedText.length; i += 1) {
      if (total + data.resolvedText[i].length <= 140) {
        result = `${result} ${data.resolvedText[i]}`;
      } else {
        result = `${result} ${data.resolvedText[i].substring(0, 160 - total)}...`;
        break;
      }
      total += data.resolvedText[i].length;
    }
    return result;
  }

  return (
    <div className={styles.news_container}>
      <div onClick={toDetail.bind(this)} style={{ cursor: 'pointer' }}>
        <h2 className={styles.news_head}>
          <span className={topState}>[置顶]</span>
          <span>{data.title}</span>
        </h2>
      </div>
      <div className={styles.news_info}>
        <span>{data.dateToString}</span>
        <span style={{ paddingLeft: 40 }}>阅读次数：{data.readNum}</span>
      </div>
      <div className={styles.news_brief} >
        {getResolvedText()}
      </div>
    </div>
  );
}

export default connect()(NewsItem);
