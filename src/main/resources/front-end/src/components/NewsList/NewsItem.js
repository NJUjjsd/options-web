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

  function getResolvedText() {
    const text = [];
    let total = 0;
    for (let i = 0; i < data.resolvedText.length; i += 1) {
      if (total + data.resolvedText[i].length <= 160) {
        text.push(data.resolvedText[i]);
      } else {
        text.push(`${data.resolvedText[i].substring(0, 160 - total)}...`);
        break;
      }
      total += data.resolvedText[i].length;
    }
    return text;
  }

  return (
    <div onClick={toDetail.bind(this)} className={styles.news_container}>
      <h2 className={styles.news_head}>{data.title}</h2>
      <div className={styles.news_info}>
        <span>{data.dateToString}</span>
        <span style={{ paddingLeft: 40 }}>阅读次数：{data.readNum}</span>
      </div>
      <div className={styles.news_brief}>
        {
        getResolvedText().map((text, i) => <p key={i}>{text}</p>)
      }
      </div>
    </div>
  );
}

export default connect()(NewsItem);
