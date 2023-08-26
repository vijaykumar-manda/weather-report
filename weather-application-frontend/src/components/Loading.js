import Skeleton, { SkeletonTheme } from "react-loading-skeleton";
import "react-loading-skeleton/dist/skeleton.css";

function Loading() {
  return (
    <>
      <h1>hello</h1>
      <SkeletonTheme color="#d1d1de" highlightColor="#">
        <section>
          <Skeleton height={50} width={50}>
            loading
          </Skeleton>
        </section>
      </SkeletonTheme>
    </>
  );
}
export default Loading;
