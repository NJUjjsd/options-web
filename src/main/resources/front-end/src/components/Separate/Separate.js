/**
 * Created by john on 2017/8/6.
 */
import React from 'react';
import { connect } from 'dva';
import styles from './Separate.css';

function Separate() {
  return (
    <div className={styles.separate} />
  );
}

export default connect()(Separate);
