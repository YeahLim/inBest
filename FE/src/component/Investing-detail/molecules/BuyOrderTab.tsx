import { formatNumberToKoreanWon } from "../../../util/formatMoney";
import IncreaseIcon from "../../common/IncreaseIcon";
import DecreaseIcon from "../../common/DecreaseIcon";
import { useOrderTab } from "./useOrderTab";

interface Props {
  expectedPrice: number;
}
const BuyOrderTab = ({ expectedPrice }: Props) => {
  const { amount, price, onChangeAmount, onChangePrice } = useOrderTab(expectedPrice);
  const profitPercentage =
    expectedPrice === 0 ? 0 : Number((((price - expectedPrice) / expectedPrice) * 100).toFixed(2));

  return (
    <div className=" flex items-center justify-around">
      <div className="">
        <div className=" flex flex-col gap-2 mt-3">
          <div className=" flex items-center justify-between">
            <p className=" text-myGray text-center">매수 수량</p>
            <div className=" flex items-center gap-4">
              <input
                type="number"
                step={1}
                min={1}
                className="p-1 border-gray-400 border bg-main bg-opacity-10 rounded-md text-right w-2/3"
                placeholder="구매할 수량"
                onChange={onChangeAmount}
                value={amount}
              />
              <p>주</p>
            </div>
          </div>
          <div className=" flex gap-2 items-center justify-between">
            <p className=" text-myGray text-center">매수 가격</p>
            <div className=" flex items-center gap-4">
              <input
                type="number"
                min={1}
                className="p-1 border-gray-400 border bg-main bg-opacity-10 rounded-md text-right w-2/3"
                placeholder="1주당 구매할 가격"
                onChange={onChangePrice}
                value={price}
              />
              <p>원</p>
            </div>
          </div>
          <div className=" mx-auto flex text-black text-opacity-80 items-center gap-2">
            <p>현재 예상 체결가는</p>
            <h5 className=" text-black font-semiBold ">{formatNumberToKoreanWon(expectedPrice || 10000)}</h5>
            <p>입니다</p>
          </div>
        </div>
      </div>
      <div className="flex flex-col gap-1 w-1/2">
        <div className=" bg-gray-300 bg-opacity-30 rounded-md p-4 flex flex-col gap-4">
          <div className=" mx-auto flex text-black text-opacity-80 items-center">
            <p>총&nbsp;&nbsp;</p>
            <h5 className=" text-black font-semiBold ">{amount}주</h5>
            <p>를&nbsp;&nbsp;</p>
            <h5 className=" text-black font-semiBold ">{formatNumberToKoreanWon(amount * price)}</h5>
            <p>에 구매합니다.</p>
          </div>
          <div className=" mx-auto flex text-black text-opacity-80 items-center text-sm">
            {profitPercentage == 0 ? (
              <p>예상 체결가에 살게요</p>
            ) : profitPercentage > 0 ? (
              <>
                <p>예상 체결가보다</p>
                <IncreaseIcon number={profitPercentage} />
                <p>더 비싸게 살게요</p>
              </>
            ) : (
              <>
                <p>예상 체결가보다</p>
                <DecreaseIcon number={profitPercentage} />
                <p>더 싸게 살게요</p>
              </>
            )}
          </div>
        </div>
        <p className=" text-sm">수수료(부가세 포함): 0.05%</p>
        <button className=" light-red-btn">매수 주문하기</button>
      </div>
    </div>
  );
};
export default BuyOrderTab;
