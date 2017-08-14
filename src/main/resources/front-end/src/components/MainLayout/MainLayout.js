/**
 * Created by john on 2017/8/4.
 */
import React from 'react';
import { Layout } from 'antd';
import styles from './MainLayout.css';
import Nav from '../../components/Nav/Nav';
import Footer from '../../components/Footer/Footer';

function MainLayout({ location, children }) {
  let backStyle = styles.white;
  if (location.pathname === '/') {
    backStyle = styles.black;
  }
  return (
    <Layout className={styles.normal}>
      <Nav location={location} />
      <div className={backStyle}>
        {children}
      </div>
      <Footer />
    </Layout>
  );
}

export default MainLayout;
